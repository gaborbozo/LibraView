import { Injectable } from '@angular/core'
import { DefaultService as TmdbApi } from '../../../generated/api/tmdb'
import { LibraConfigKeys, LibraConfigService } from './libra-config.service'

@Injectable({
  providedIn: 'root',
})
export class CinematicMetaService {
  constructor(
    tmdbApi: TmdbApi,
    private libraConfig: LibraConfigService,
  ) {
    if (
      !libraConfig.getResource<LibraConfigKeys.CINEMATIC_META_DETAILS>(
        LibraConfigKeys.CINEMATIC_META_DETAILS,
      )
    ) {
      tmdbApi
        .configurationDetails()
        .subscribe((response) =>
          libraConfig.setResource<LibraConfigKeys.CINEMATIC_META_DETAILS>(
            LibraConfigKeys.CINEMATIC_META_DETAILS,
            response,
          ),
        )
    }
  }

  getBaseUrl() {
    return this.libraConfig.getResource<LibraConfigKeys.CINEMATIC_META_DETAILS>(
      LibraConfigKeys.CINEMATIC_META_DETAILS,
    )?.images?.base_url
  }
}
