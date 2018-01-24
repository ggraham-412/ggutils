package org.ggraham.ggutils.message;

public class PacketFieldConfig {
	private final FieldType m_fieldType;
	private final String m_encoding;
	private final int m_fixedLength;
	private final String m_strValue;

	public static final String DEFAULT_ENCODING = null;
	public static final String DEFAULT_STRING_ENCODING = "US-ASCII";
	public static final int FL_VARIABLELENGTH = 0;
	public static final int FL_ALLREMAINING = -1;
	public static final int DEFAULT_FIXED_LENGTH = FL_VARIABLELENGTH;

	public FieldType getFieldType() {
		return m_fieldType;
	}

	public String getEncoding() {
		return m_encoding;
	}
	
	public int getFixedLength() { 
		return m_fixedLength;
	}
	
	public boolean isAllRemaining() {
		return m_fixedLength == FL_ALLREMAINING;
	}

	public boolean isFixedLength() {
		return m_fixedLength != FL_VARIABLELENGTH && m_fixedLength != FL_ALLREMAINING;
	}

	public boolean isVariableLength() {
		return m_fixedLength == FL_VARIABLELENGTH;
	}

	public PacketFieldConfig(FieldType ft, String enc, int fixedLength) {
		m_fieldType = ft;
		m_fixedLength = fixedLength;
		if (m_fieldType == FieldType.STRING) {
			if (enc == null || enc.isEmpty()) {
				// Require strings to at least have default encoding
				enc = DEFAULT_STRING_ENCODING;
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
		this(ft, DEFAULT_ENCODING, DEFAULT_FIXED_LENGTH);
	}

	public PacketFieldConfig(FieldType ft, int fixedLength) {
		this(ft, DEFAULT_ENCODING, fixedLength);
	}

	public PacketFieldConfig(FieldType ft, String enc) {
		this(ft, enc, DEFAULT_FIXED_LENGTH);
	}

	public PacketFieldConfig(String ft, String enc, int fixedLength) {
		this(FieldType.valueOf(ft), enc, fixedLength);
	}

	public PacketFieldConfig(String ft, String enc) {
		this(FieldType.valueOf(ft), enc, DEFAULT_FIXED_LENGTH);
	}

	public PacketFieldConfig(String ft, int fixedLength) {
		this(FieldType.valueOf(ft), DEFAULT_ENCODING, fixedLength);
	}

	public PacketFieldConfig(String ft) {
		this(FieldType.valueOf(ft));
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
