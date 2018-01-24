package org.ggraham.ggutils.message;

public class PacketFieldConfig {
	private final FieldType m_fieldType;
	private final String m_encoding;
	private final String m_strValue;

	public static final String DEFAULT_ENCODING = "US-ASCII";

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
