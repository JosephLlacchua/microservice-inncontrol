package com.inncontrol.gateway.beans;


import com.inncontrol.gateway.filters.AuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class GatewayBeans {

    private final AuthFilter authFilter;

}
