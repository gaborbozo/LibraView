package hu.bozgab.cinematic.controller;

import hu.bozgab.cinematic.dto.GenreDTO;
import hu.bozgab.cinematic.dto.IdRequest;
import hu.bozgab.cinematic.dto.SimpleResponse;
import hu.bozgab.cinematic.service.CinematicService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/cinematic_module/cinematic")
@RestController
public class CinematicManageController {

    private final CinematicService cinematicManagementService;


    @GetMapping("/updateGenres")
    public ResponseEntity<List<GenreDTO>> updateGenres() {
        return ResponseEntity.ok(cinematicManagementService.updateGenres());
    }

    @GetMapping("/availableGenres")
    public ResponseEntity<List<GenreDTO>> availableGenres() {
        return ResponseEntity.ok(cinematicManagementService.availableGenres());
    }

    @PostMapping("/addCinematic")
    public ResponseEntity<SimpleResponse> addCinematic(@RequestBody IdRequest request) {
        cinematicManagementService.addCinematic(request);
        return ResponseEntity.ok(SimpleResponse.builder().message("ok").build());
    }

}
