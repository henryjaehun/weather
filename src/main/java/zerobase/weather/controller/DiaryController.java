package zerobase.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Diary Controller", description = "날씨 일기에 관련된 CRUD API")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    //Post 사용해서 create/diary 라는 path 에다가 요청을 보냈을 때 PostMapping 함수에 들어오게 됨
    @PostMapping("/create/diary")
    @Operation(summary = "일기 생성", description = "특정 날짜에 일기를 생성합니다.")
    void createDiary //1.파라미터 형식으로 요청할때 필요한 데이트라는 로칼데이트형식의 값, 2.바디값으로 보내주어야할 스트링 형식의 테스트 가 필요
    (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
     @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @GetMapping("/read/diary")
    @Operation(summary = "일기 조회", description = "특정 날짜의 일기를 조회합니다.")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return diaryService.readDiary(date);
    }

    @GetMapping("/read/diaries")
    @Operation(summary = "다수의 일기 조회", description = "특정 기간의 일기를 조회합니다.")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @PutMapping("/update/diary")
    @Operation(summary = "일기 내용 수정", description = "특정 날짜의 일기를 수정합니다.")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @DeleteMapping("/delete/diary")
    @Operation(summary = "일기 삭제", description = "특정 날짜의 일기를 삭제합니다.")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
