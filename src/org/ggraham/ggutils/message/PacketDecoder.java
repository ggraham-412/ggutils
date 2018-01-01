package org.ggraham.ggutils.message;

/*
 * 
 * Apache License 2.0 
 * 
 * Copyright (c) [2017] [Gregory Graham]
 * 
 * See LICENSE.txt for details.
 * 
 */

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.ggraham.ggutils.PackageService;

/**
 * 
 * Encodes/Decodes UDP packet payload as sequence of simple fields.
 * 
 * @author ggraham
 *
 */
public class PacketDecoder {

	/**
	 * 
	 * Contains configuration data for fields in UDP packet payload that is in a
	 * java.nio.ByteBuffer. Supports simple data types: int, long, float, double,
	 * rawbytes, and string (with encoding). (Rawbytes and strings are automatically
	 * encoded along with an int length field.)
	 * 
	 * The implementation is a list of fields in the packet in order they are added.
	 * Object array containing data should have length equal to the number of
	 * fields, and are packed/unpacked by iterating over the fields.
	 * 
	 * @author ggraham
	 *
	 */
	public static class PacketFieldConfig {

		private final FieldType m_fieldType;
		private final String m_encoding;
		private final String m_strValue;

		public FieldType getFieldType() {
			return m_fieldType;
		}

		public String getEncoding() {
			return m_encoding;
		}

		public PacketFieldConfig(FieldType ft, String enc) {
			m_fieldType = ft;
			if (m_fieldType == FieldType.STRING) {
				if (enc == null || enc.isEmpty()) {
					// Require strings to at least have default encoding
					enc = DEFAULT_ENCODING;
				}
			}
			m_encoding = enc;
			if (m_encoding != null && !m_encoding.isEmpty()) {
				m_strValue = m_fieldType + "__" + m_encoding;
			} else {
				m_strValue = m_fieldType.toString();
			}
		}

		public PacketFieldConfig(FieldType ft) {
			this(ft, null);
		}

		public PacketFieldConfig(String ft, String enc) {
			this(FieldType.valueOf(ft), enc);
		}

		public PacketFieldConfig(String ft) {
			this(ft, null);
		}

		@Override
		public String toString() {
			return m_strValue;
		}

		public static PacketFieldConfig fromString(String strVal) {
			String[] parts = strVal.split("__");
			if (parts.length > 1) {
				return new PacketFieldConfig(parts[0], parts[1]);
			} else {
				return new PacketFieldConfig(parts[0]);
			}
		}

	}

	public enum FieldType {
		INTEGER, LONG, STRING, FLOAT, DOUBLE, RAWBYTES
	}

	public static final String DEFAULT_ENCODING = "US-ASCII";

	private final List<PacketFieldConfig> m_structure = new ArrayList<PacketFieldConfig>();

	/**
	 * Adds a field to the list of fields
	 * 
	 * @param ft
	 */
	public void addField(PacketFieldConfig ft) {
		m_structure.add(ft);
		PackageService.getLog().logDebug("PacketDecoder.addField", this.toString());
	}

	/**
	 * Adds an int to the list of fields
	 */
	public void addInt() {
		addField(new PacketFieldConfig(FieldType.INTEGER));
	}

	/**
	 * Adds a long to the list of fields
	 */
	public void addLong() {
		addField(new PacketFieldConfig(FieldType.LONG));
	}

	/**
	 * Adds a string to the list of fields
	 */
	public void addString() {
		addField(new PacketFieldConfig(FieldType.STRING));
	}

	/**
	 * Adds a string with alternate encoding
	 * 
	 * @param encoding
	 *            : the character encoding to use
	 */
	public void addString(String encoding) {
		addField(new PacketFieldConfig(FieldType.STRING, encoding));
	}

	/**
	 * Adds a float to the list of fields
	 */
	public void addFloat() {
		addField(new PacketFieldConfig(FieldType.FLOAT));
	}

	/**
	 * Adds a double to the list of fields
	 */
	public void addDouble() {
		addField(new PacketFieldConfig(FieldType.DOUBLE));
	}

	/**
	 * Adds a field of raw bytes to the list of fields
	 */
	public void addRawBytes() {
		addField(new PacketFieldConfig(FieldType.RAWBYTES));
	}

	/**
	 * Clears the list of fields
	 */
	public void clear() {
		m_structure.clear();
	}

	/**
	 * 
	 * Encodes a packet with field values taken from the object array
	 * 
	 * @param fields
	 *            : array of field values to encode
	 * @param packet
	 *            : packet buffer
	 * 
	 */
	public void EncodePacket(Object[] fields, ByteBuffer packet) {
		for (int i = 0; i < m_structure.size(); i++) {
			try {
				switch (m_structure.get(i).getFieldType()) {
				case INTEGER:
					packet.putInt((int) fields[i]);
					break;
				case LONG:
					packet.putLong((long) fields[i]);
					break;
				case STRING:
					String str = (String) fields[i];
					byte[] strBytes = str.getBytes(Charset.forName(m_structure.get(i).getEncoding()));
					packet.putInt(strBytes.length);
					packet.put(strBytes);
					break;
				case FLOAT:
					packet.putFloat((float) fields[i]);
					break;
				case DOUBLE:
					packet.putDouble((double) fields[i]);
					break;
				case RAWBYTES:
					byte[] buffer = (byte[]) fields[i];
					packet.putInt(buffer.length);
					packet.put(buffer);
					break;
				}
			} catch (Exception ex) {
				PackageService.getLog().logError("PacketDecoder.EncodePacket",
						"Caught exception converting field " + i + ", " + m_structure.get(i).getFieldType().toString()
								+ ", value " + fields[i].getClass() + ", " + fields[i] + ": " + ex);
			}
		}
	}

	/**
	 * 
	 * Decodes field values from the ByteBuffer into the given object array
	 * 
	 * @param packet
	 *            : packet buffer
	 * @param fields
	 *            : Array equal in length of number of fields
	 */
	public void DecodePacket(ByteBuffer packet, Object[] fields) {
		for (int i = 0; i < m_structure.size(); i++) {
			try {
				switch (m_structure.get(i).getFieldType()) {
				case INTEGER:
					fields[i] = packet.getInt();
					break;
				case LONG:
					fields[i] = packet.getLong();
					break;
				case STRING:
					byte[] bytes = new byte[packet.getInt()];
					packet.get(bytes, 0, bytes.length);
					fields[i] = new String(bytes, Charset.forName(m_structure.get(i).getEncoding()));
					break;
				case FLOAT:
					fields[i] = packet.getFloat();
					break;
				case DOUBLE:
					fields[i] = packet.getDouble();
					break;
				case RAWBYTES:
					int len = packet.getInt();
					byte[] buffer = new byte[len];
					packet.get(buffer, 0, len);
					fields[i] = buffer;
					break;
				}
			} catch (Exception ex) {
				PackageService.getLog().logError("PacketDecoder.DecodePacket", 
						"Caught exception converting field " + i + ", " + m_structure.get(i).getFieldType().toString()
								+ ", value " + fields[i].getClass() + ", " + fields[i] + ": " + ex);
			}
		}
	}

}
