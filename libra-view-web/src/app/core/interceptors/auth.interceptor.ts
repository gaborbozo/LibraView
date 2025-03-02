import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { LibraConfigKeys, LibraConfigService } from '../services/libra-config.service'

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private configService: LibraConfigService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.configService.getResource(LibraConfigKeys.TOKEN)

    if (token) {
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      })
      return next.handle(authReq)
    }

    return next.handle(req)
  }
}
