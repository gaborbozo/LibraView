import { Injectable } from '@angular/core'
import { ConfigurationDetails } from '../../constants/cinematic.alias'

export enum LibraConfigKeys {
  'LIBRA_TOKEN' = 'libraToken',
  'TMDB_TOKEN' = 'tmdbToken',

  'CINEMATIC_META_DETAILS' = 'cinematicMetaDetails',
}

export interface LibraResourceKeyValue {
  [LibraConfigKeys.LIBRA_TOKEN]: string
  [LibraConfigKeys.TMDB_TOKEN]: string
  [LibraConfigKeys.CINEMATIC_META_DETAILS]: ConfigurationDetails
}

const requiresStringify = (key: LibraConfigKeys): boolean =>
  [LibraConfigKeys.CINEMATIC_META_DETAILS].includes(key)

@Injectable({
  providedIn: 'root',
})
export class LibraConfigService {
  getResource<K extends LibraConfigKeys>(key: LibraConfigKeys): LibraResourceKeyValue[K] | null {
    if (typeof window === 'undefined') {
      return null
    }

    const value = localStorage.getItem(key)
    if (!value) {
      console.error(`${key} is not declared in local storage!`)
      return value
    }
    return requiresStringify(key) ? (JSON.parse(value) as LibraResourceKeyValue[K]) : value
  }

  setResource<K extends LibraConfigKeys>(key: LibraConfigKeys, value: LibraResourceKeyValue[K]) {
    const resource = requiresStringify(key) ? JSON.stringify(value) : (value as string)
    localStorage.setItem(key, resource)
  }

  emptyResource(key: LibraConfigKeys) {
    localStorage.removeItem(key)
  }
}
