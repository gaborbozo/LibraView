import {
  ConfigurationDetails200Response,
  ConfigurationDetails200ResponseImages,
  SearchMovie200Response,
  SearchMovie200ResponseResultsInner,
  SearchTv200Response,
  SearchTv200ResponseResultsInner,
} from '../../generated/api/tmdb'

// Config

export type ConfigurationDetails = ConfigurationDetails200Response
export type ConfigurationDetailsImages = ConfigurationDetails200ResponseImages

// Search

export type AliasMovieSearchResponse = SearchMovie200Response
export type AliasMovieSearchResponseItem = SearchMovie200ResponseResultsInner

export type AliasSeriesSearchResponse = SearchTv200Response
export type AliasSeriesSearchResponseItem = SearchTv200ResponseResultsInner
