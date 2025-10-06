import { Component, OnInit } from '@angular/core'

@Component({
  selector: 'app-cinematic-library',
  templateUrl: './library.component.html',
  styleUrl: './library.component.scss',
  standalone: false,
})
export class CinematicLibraryComponent implements OnInit {
  // items: Cinematic[] = []

  constructor() {}

  ngOnInit(): void {
    // this.cinematicClient.getCinematic().subscribe((response) => {
    //   this.items = response.cinematics
    // })
  }
}
