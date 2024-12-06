package com.marine.traffic.res;

import com.marine.traffic.model.MarineTraffic;
import com.marine.traffic.model.ResponseDto;
import com.marine.traffic.service.MarineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/marine-traffic")
public class MarineController {
    private final MarineService marineService;

    public MarineController(MarineService marineService) {
        this.marineService = marineService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> createMarineTraffic(@RequestBody MarineTraffic payload) {

        return ResponseEntity.ok(marineService.createMarineTraffic(payload));
    }

    @PostMapping("/batch")
    public ResponseEntity<ResponseDto> createBatchMarineTraffic(@RequestBody List<MarineTraffic> payload) {

        return ResponseEntity.ok(marineService.createMarineTrafficBatch(payload));
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> getListMarineTraffic() {

        return ResponseEntity.ok(marineService.getListMarineTraffic());
    }
    @GetMapping("/detail")
    public ResponseEntity<ResponseDto> getMarineTrafficDetail(@RequestParam Integer maritimeMobileServiceId) {

        return ResponseEntity.ok(marineService.getMarineTrafficDetail(maritimeMobileServiceId));
    }

    @GetMapping("/auto-create-data")
    public ResponseEntity<ResponseDto> getCreateDataFake() {

        return ResponseEntity.ok(marineService.getCreateDataFake(10));
    }

}
