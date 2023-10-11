package io.ssegclient.core;


import io.ssegclient.webclient.JwtApiClient;
import io.ssegclient.webclient.repository.JwtTokenRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class SsegClient {
    
    private final ClientAppProperties properties;
    private final JwtTokenRepository repository;
    private final JwtApiClient jwtApiClient;
    
    
    
}
