package hu.bozgab.libraview.cineregistry.tmdb;


import hu.bozgab.libraview.cineregistry.generated.api.DefaultApi;
import hu.bozgab.libraview.cineregistry.generated.client.ApiClient;
import org.springframework.stereotype.Component;


@Component
public class TMDBClient extends DefaultApi {

    public TMDBClient(ApiClient apiClient) {
        super(apiClient);
    }

}
