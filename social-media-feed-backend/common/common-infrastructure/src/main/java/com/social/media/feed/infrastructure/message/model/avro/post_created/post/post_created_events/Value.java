/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package post_created.post.post_created_events;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Value extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4062598753564259832L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Value\",\"namespace\":\"post_created.post.post_created_events\",\"fields\":[{\"name\":\"event_id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\",\"connect.version\":1,\"connect.name\":\"io.debezium.data.Uuid\"}},{\"name\":\"created_at\",\"type\":{\"type\":\"long\",\"connect.version\":1,\"connect.name\":\"io.debezium.time.MicroTimestamp\"}},{\"name\":\"payload\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\",\"connect.version\":1,\"connect.name\":\"io.debezium.data.Json\"}}],\"connect.name\":\"post_created.post.post_created_events.Value\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Value> ENCODER =
      new BinaryMessageEncoder<Value>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Value> DECODER =
      new BinaryMessageDecoder<Value>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Value> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Value> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Value> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Value>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Value to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Value from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Value instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Value fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String event_id;
  private long created_at;
  private java.lang.String payload;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Value() {}

  /**
   * All-args constructor.
   * @param event_id The new value for event_id
   * @param created_at The new value for created_at
   * @param payload The new value for payload
   */
  public Value(java.lang.String event_id, java.lang.Long created_at, java.lang.String payload) {
    this.event_id = event_id;
    this.created_at = created_at;
    this.payload = payload;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return event_id;
    case 1: return created_at;
    case 2: return payload;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: event_id = value$ != null ? value$.toString() : null; break;
    case 1: created_at = (java.lang.Long)value$; break;
    case 2: payload = value$ != null ? value$.toString() : null; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'event_id' field.
   * @return The value of the 'event_id' field.
   */
  public java.lang.String getEventId() {
    return event_id;
  }


  /**
   * Sets the value of the 'event_id' field.
   * @param value the value to set.
   */
  public void setEventId(java.lang.String value) {
    this.event_id = value;
  }

  /**
   * Gets the value of the 'created_at' field.
   * @return The value of the 'created_at' field.
   */
  public long getCreatedAt() {
    return created_at;
  }


  /**
   * Sets the value of the 'created_at' field.
   * @param value the value to set.
   */
  public void setCreatedAt(long value) {
    this.created_at = value;
  }

  /**
   * Gets the value of the 'payload' field.
   * @return The value of the 'payload' field.
   */
  public java.lang.String getPayload() {
    return payload;
  }


  /**
   * Sets the value of the 'payload' field.
   * @param value the value to set.
   */
  public void setPayload(java.lang.String value) {
    this.payload = value;
  }

  /**
   * Creates a new Value RecordBuilder.
   * @return A new Value RecordBuilder
   */
  public static post_created.post.post_created_events.Value.Builder newBuilder() {
    return new post_created.post.post_created_events.Value.Builder();
  }

  /**
   * Creates a new Value RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Value RecordBuilder
   */
  public static post_created.post.post_created_events.Value.Builder newBuilder(post_created.post.post_created_events.Value.Builder other) {
    if (other == null) {
      return new post_created.post.post_created_events.Value.Builder();
    } else {
      return new post_created.post.post_created_events.Value.Builder(other);
    }
  }

  /**
   * Creates a new Value RecordBuilder by copying an existing Value instance.
   * @param other The existing instance to copy.
   * @return A new Value RecordBuilder
   */
  public static post_created.post.post_created_events.Value.Builder newBuilder(post_created.post.post_created_events.Value other) {
    if (other == null) {
      return new post_created.post.post_created_events.Value.Builder();
    } else {
      return new post_created.post.post_created_events.Value.Builder(other);
    }
  }

  /**
   * RecordBuilder for Value instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Value>
    implements org.apache.avro.data.RecordBuilder<Value> {

    private java.lang.String event_id;
    private long created_at;
    private java.lang.String payload;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(post_created.post.post_created_events.Value.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.event_id)) {
        this.event_id = data().deepCopy(fields()[0].schema(), other.event_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.created_at)) {
        this.created_at = data().deepCopy(fields()[1].schema(), other.created_at);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.payload)) {
        this.payload = data().deepCopy(fields()[2].schema(), other.payload);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing Value instance
     * @param other The existing instance to copy.
     */
    private Builder(post_created.post.post_created_events.Value other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.event_id)) {
        this.event_id = data().deepCopy(fields()[0].schema(), other.event_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.created_at)) {
        this.created_at = data().deepCopy(fields()[1].schema(), other.created_at);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.payload)) {
        this.payload = data().deepCopy(fields()[2].schema(), other.payload);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'event_id' field.
      * @return The value.
      */
    public java.lang.String getEventId() {
      return event_id;
    }


    /**
      * Sets the value of the 'event_id' field.
      * @param value The value of 'event_id'.
      * @return This builder.
      */
    public post_created.post.post_created_events.Value.Builder setEventId(java.lang.String value) {
      validate(fields()[0], value);
      this.event_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'event_id' field has been set.
      * @return True if the 'event_id' field has been set, false otherwise.
      */
    public boolean hasEventId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'event_id' field.
      * @return This builder.
      */
    public post_created.post.post_created_events.Value.Builder clearEventId() {
      event_id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'created_at' field.
      * @return The value.
      */
    public long getCreatedAt() {
      return created_at;
    }


    /**
      * Sets the value of the 'created_at' field.
      * @param value The value of 'created_at'.
      * @return This builder.
      */
    public post_created.post.post_created_events.Value.Builder setCreatedAt(long value) {
      validate(fields()[1], value);
      this.created_at = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'created_at' field has been set.
      * @return True if the 'created_at' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'created_at' field.
      * @return This builder.
      */
    public post_created.post.post_created_events.Value.Builder clearCreatedAt() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'payload' field.
      * @return The value.
      */
    public java.lang.String getPayload() {
      return payload;
    }


    /**
      * Sets the value of the 'payload' field.
      * @param value The value of 'payload'.
      * @return This builder.
      */
    public post_created.post.post_created_events.Value.Builder setPayload(java.lang.String value) {
      validate(fields()[2], value);
      this.payload = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'payload' field has been set.
      * @return True if the 'payload' field has been set, false otherwise.
      */
    public boolean hasPayload() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'payload' field.
      * @return This builder.
      */
    public post_created.post.post_created_events.Value.Builder clearPayload() {
      payload = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Value build() {
      try {
        Value record = new Value();
        record.event_id = fieldSetFlags()[0] ? this.event_id : (java.lang.String) defaultValue(fields()[0]);
        record.created_at = fieldSetFlags()[1] ? this.created_at : (java.lang.Long) defaultValue(fields()[1]);
        record.payload = fieldSetFlags()[2] ? this.payload : (java.lang.String) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Value>
    WRITER$ = (org.apache.avro.io.DatumWriter<Value>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Value>
    READER$ = (org.apache.avro.io.DatumReader<Value>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.event_id);

    out.writeLong(this.created_at);

    out.writeString(this.payload);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.event_id = in.readString();

      this.created_at = in.readLong();

      this.payload = in.readString();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.event_id = in.readString();
          break;

        case 1:
          this.created_at = in.readLong();
          break;

        case 2:
          this.payload = in.readString();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










