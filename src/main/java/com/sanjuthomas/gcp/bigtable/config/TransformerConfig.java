package com.sanjuthomas.gcp.bigtable.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.kafka.common.annotation.InterfaceStability.Stable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.MoreObjects;

/**
 * 
 * In memory representation of Transformer configuration.
 *
 * @author Sanju Thomas
 * @since 1.0.3
 *
 */
@Stable
public class TransformerConfig {

  private static final Logger logger = LoggerFactory.getLogger(TransformerConfig.class);
  private final List<String> keyQualifiers;
  private final String keyDelimiter;
  private final List<String> families;
  private final Map<String, List<String>> familyToQualifierMapping;
  
  public TransformerConfig(final List<String> keyQualifiers, final String keyDelimiter,
      final List<String> families, final Map<String, List<String>> familyToQualifierMapping) {
    logger.info("TransformerConfig is created by task id {}", Thread.currentThread().getId());
    this.keyQualifiers = keyQualifiers;
    this.keyDelimiter = keyDelimiter;
    this.families = families;
    this.familyToQualifierMapping = familyToQualifierMapping;
  }

  public List<String> familyQualifiers(final String family) {
    return MoreObjects.firstNonNull(this.familyToQualifierMapping.get(family),
        new ArrayList<String>(0));
  }

  public List<String> families() {
    return this.families;
  }

  public List<String> keyQualifies() {
    return MoreObjects.firstNonNull(this.keyQualifiers, new ArrayList<String>(0));
  }

  public String keyDelimiter() {
    return MoreObjects.firstNonNull(this.keyDelimiter, "_");
  }
}
