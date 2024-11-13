package com.giffing.wicket.spring.boot.starter.configuration.extensions.stuff.datastore.cassandra;

import com.giffing.wicket.spring.boot.context.extensions.types.DurationUnit;
import com.giffing.wicket.spring.boot.context.extensions.types.SessionUnit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = DataStoreCassandraProperties.PROPERTY_PREFIX)
@Getter
@Setter
public class DataStoreCassandraProperties {

    public static final String PROPERTY_PREFIX = "wicket.stuff.datastore.cassandra";

    private boolean enabled = true;

    private List<String> contactPoints;

    private String tableName = "pagestore";

    private String keyspaceName = "wicket";

    private Long recordTtl = 30L;

    private DurationUnit recordTtlUnit = DurationUnit.MINUTES;

    private Long sessionSize = 2L;

    private SessionUnit sessionUnit = SessionUnit.MEGABYTES;

}
