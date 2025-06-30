package hu.bozgab.libraviewtmdb.tmdb;


import hu.bozgab.tmdb.generated.api.DefaultApi;
import hu.bozgab.tmdb.generated.client.ApiClient;
import org.springframework.stereotype.Component;


@Component
public class TMDBClient extends DefaultApi {

    public TMDBClient(ApiClient apiClient) {
        super(apiClient);
    }

}
