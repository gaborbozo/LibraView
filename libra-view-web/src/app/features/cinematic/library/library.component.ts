import { Component, OnInit } from '@angular/core'
import { CinematicClientService } from '../../../shared/client-service/cinematic-client.service'
import { Cinematic } from '../../../shared/data-model/cinematic/cinematic'

@Component({
  selector: 'app-cinematic-library',
  templateUrl: './library.component.html',
  styleUrl: './library.component.scss',
})
export class CinematicLibraryComponent implements OnInit {
  items: Cinematic[] = []

  constructor(private cinematicClient: CinematicClientService) {}

  ngOnInit(): void {
    this.cinematicClient.getCinematic().subscribe((response) => {
      this.items = response.cinematics
    })
  }
}
