import { Injectable, NgZone } from '@angular/core'
import { Observable } from 'rxjs'

@Injectable({ providedIn: 'root' })
export class ServerSentEventsService {
  constructor(private zone: NgZone) {}

  connect<T = string>(url: string): Observable<T> {
    return new Observable<T>((observer) => {
      const eventSource = new EventSource(url)

      // Handle normal 'message' events
      eventSource.onmessage = (event) => {
        this.zone.run(() => {
          try {
            const parsed = JSON.parse(event.data)
            observer.next(parsed)
          } catch {
            observer.next(event.data as unknown as T)
          }
        })
      }

      // Handle errors
      eventSource.onerror = (error) => {
        this.zone.run(() => observer.error(error))
        eventSource.close()
      }

      // Optional: handle named custom events
      // eventSource.addEventListener('done', (event: MessageEvent) => {
      //   this.zone.run(() => observer.next(event.data as T));
      // });

      // Cleanup
      return () => {
        eventSource.close()
      }
    })
  }
}
