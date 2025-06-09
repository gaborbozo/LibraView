package hu.bozgab.LibraViewTMDB.tmdb;

import org.openapitools.client.ApiClient;
import org.openapitools.client.api.DefaultApi;
import org.springframework.stereotype.Component;


@Component
public class TMDBClient extends DefaultApi {

    public TMDBClient(ApiClient apiClient) {
        super(apiClient);
    }

}
