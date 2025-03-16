import { Component, OnInit } from '@angular/core'
import { CinematicClientService } from '../../../shared/client-service/cinematic-client.service'

@Component({
  selector: 'app-cinematic-library',
  templateUrl: './library.component.html',
  styleUrl: './library.component.scss',
})
export class CinematicLibraryComponent implements OnInit {
  constructor(private cinematicClient: CinematicClientService) {}

  ngOnInit(): void {
    this.cinematicClient.getItems().subscribe()
  }
}
