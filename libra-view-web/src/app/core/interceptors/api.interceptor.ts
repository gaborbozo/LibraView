import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'

@Injectable()
export class ApiInterceptor implements HttpInterceptor {
  // TODO replace into configuration
  private baseUrl = 'http://localhost:8080'

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const modifiedReq = request.clone({
      url: `${this.baseUrl}${request.url}`,
      withCredentials: true,
    })

    return next.handle(modifiedReq)
  }
}
