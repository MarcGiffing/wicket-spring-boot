package com.giffing.wicket.spring.boot.example.repository.services.customer.filter;

import com.giffing.wicket.spring.boot.example.repository.DefaultFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerFilter extends DefaultFilter {

    private Long id;

    /**
     * Filtering for the exact user name
     */
    private String username;

    /**
     * Filtering by lowercase username with wildcards at the start and the end.
     */
    private String usernameLike;

    private String firstnameLike;

    private String lastnameLike;

    private boolean active;

}
