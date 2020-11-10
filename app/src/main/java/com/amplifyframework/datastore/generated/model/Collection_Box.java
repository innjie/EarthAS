package com.amplifyframework.datastore.generated.model;


import java.util.UUID;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Collection_Box type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Collection_Boxes")
public final class Collection_Box implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField REGION = field("region");
  public static final QueryField GENERAL = field("general");
  public static final QueryField PAPER = field("paper");
  public static final QueryField PLASTIC = field("plastic");
  public static final QueryField GLASS = field("glass");
  public static final QueryField CAN = field("can");
  public static final QueryField VINYL = field("vinyl");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String region;
  private final @ModelField(targetType="Boolean") Boolean general;
  private final @ModelField(targetType="Boolean") Boolean paper;
  private final @ModelField(targetType="Boolean") Boolean plastic;
  private final @ModelField(targetType="Boolean") Boolean glass;
  private final @ModelField(targetType="Boolean") Boolean can;
  private final @ModelField(targetType="Boolean") Boolean vinyl;
  public String getId() {
      return id;
  }
  
  public String getRegion() {
      return region;
  }
  
  public Boolean getGeneral() {
      return general;
  }
  
  public Boolean getPaper() {
      return paper;
  }
  
  public Boolean getPlastic() {
      return plastic;
  }
  
  public Boolean getGlass() {
      return glass;
  }
  
  public Boolean getCan() {
      return can;
  }
  
  public Boolean getVinyl() {
      return vinyl;
  }
  
  private Collection_Box(String id, String region, Boolean general, Boolean paper, Boolean plastic, Boolean glass, Boolean can, Boolean vinyl) {
    this.id = id;
    this.region = region;
    this.general = general;
    this.paper = paper;
    this.plastic = plastic;
    this.glass = glass;
    this.can = can;
    this.vinyl = vinyl;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Collection_Box collectionBox = (Collection_Box) obj;
      return ObjectsCompat.equals(getId(), collectionBox.getId()) &&
              ObjectsCompat.equals(getRegion(), collectionBox.getRegion()) &&
              ObjectsCompat.equals(getGeneral(), collectionBox.getGeneral()) &&
              ObjectsCompat.equals(getPaper(), collectionBox.getPaper()) &&
              ObjectsCompat.equals(getPlastic(), collectionBox.getPlastic()) &&
              ObjectsCompat.equals(getGlass(), collectionBox.getGlass()) &&
              ObjectsCompat.equals(getCan(), collectionBox.getCan()) &&
              ObjectsCompat.equals(getVinyl(), collectionBox.getVinyl());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getRegion())
      .append(getGeneral())
      .append(getPaper())
      .append(getPlastic())
      .append(getGlass())
      .append(getCan())
      .append(getVinyl())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Collection_Box {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("region=" + String.valueOf(getRegion()) + ", ")
      .append("general=" + String.valueOf(getGeneral()) + ", ")
      .append("paper=" + String.valueOf(getPaper()) + ", ")
      .append("plastic=" + String.valueOf(getPlastic()) + ", ")
      .append("glass=" + String.valueOf(getGlass()) + ", ")
      .append("can=" + String.valueOf(getCan()) + ", ")
      .append("vinyl=" + String.valueOf(getVinyl()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Collection_Box justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Collection_Box(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      region,
      general,
      paper,
      plastic,
      glass,
      can,
      vinyl);
  }
  public interface BuildStep {
    Collection_Box build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep region(String region);
    BuildStep general(Boolean general);
    BuildStep paper(Boolean paper);
    BuildStep plastic(Boolean plastic);
    BuildStep glass(Boolean glass);
    BuildStep can(Boolean can);
    BuildStep vinyl(Boolean vinyl);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String region;
    private Boolean general;
    private Boolean paper;
    private Boolean plastic;
    private Boolean glass;
    private Boolean can;
    private Boolean vinyl;
    @Override
     public Collection_Box build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Collection_Box(
          id,
          region,
          general,
          paper,
          plastic,
          glass,
          can,
          vinyl);
    }
    
    @Override
     public BuildStep region(String region) {
        this.region = region;
        return this;
    }
    
    @Override
     public BuildStep general(Boolean general) {
        this.general = general;
        return this;
    }
    
    @Override
     public BuildStep paper(Boolean paper) {
        this.paper = paper;
        return this;
    }
    
    @Override
     public BuildStep plastic(Boolean plastic) {
        this.plastic = plastic;
        return this;
    }
    
    @Override
     public BuildStep glass(Boolean glass) {
        this.glass = glass;
        return this;
    }
    
    @Override
     public BuildStep can(Boolean can) {
        this.can = can;
        return this;
    }
    
    @Override
     public BuildStep vinyl(Boolean vinyl) {
        this.vinyl = vinyl;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String region, Boolean general, Boolean paper, Boolean plastic, Boolean glass, Boolean can, Boolean vinyl) {
      super.id(id);
      super.region(region)
        .general(general)
        .paper(paper)
        .plastic(plastic)
        .glass(glass)
        .can(can)
        .vinyl(vinyl);
    }
    
    @Override
     public CopyOfBuilder region(String region) {
      return (CopyOfBuilder) super.region(region);
    }
    
    @Override
     public CopyOfBuilder general(Boolean general) {
      return (CopyOfBuilder) super.general(general);
    }
    
    @Override
     public CopyOfBuilder paper(Boolean paper) {
      return (CopyOfBuilder) super.paper(paper);
    }
    
    @Override
     public CopyOfBuilder plastic(Boolean plastic) {
      return (CopyOfBuilder) super.plastic(plastic);
    }
    
    @Override
     public CopyOfBuilder glass(Boolean glass) {
      return (CopyOfBuilder) super.glass(glass);
    }
    
    @Override
     public CopyOfBuilder can(Boolean can) {
      return (CopyOfBuilder) super.can(can);
    }
    
    @Override
     public CopyOfBuilder vinyl(Boolean vinyl) {
      return (CopyOfBuilder) super.vinyl(vinyl);
    }
  }
  
}
