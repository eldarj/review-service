package com.ejahijagic.reviewservice.caching;

import com.ejahijagic.reviewservice.entity.ProductReviewDocument;
import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class HazelcastConfiguration {

    @Bean
    HazelcastInstance clientHazelcastInstance() {
        return Hazelcast.newHazelcastInstance(config());
    }

    @Bean
    public IMap<String, ProductReviewDocument> cachedProductReviews(HazelcastInstance clientHazelcastInstance) {
        return clientHazelcastInstance.getMap("product-reviews");
    }

    Config config() {
        Config config = new Config();
        JoinConfig network = config.getNetworkConfig().getJoin();
        network.getMulticastConfig().setEnabled(false);
        network.getTcpIpConfig().addMember("127.0.0.1").setEnabled(true);

        return config;
    }
}
