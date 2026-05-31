package com.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}
package com.example.apartmentexchange.controller;

import com.example.apartmentexchange.model.Request;
import com.example.apartmentexchange.service.RequestService;
import org.springframework.web.bind.annotation.*;
        import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RequestController {

    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Object add(@RequestBody Request request) {
        return service.addRequest(request);
    }

// ... інші методи контролера


// ... фрагмент циклу перебору заявок у RequestService.java
for (Request r : all) {
        if (r.getId().equals(current.getId())) continue;

        // ... перевірка basicMatch (кімнати та райони) ...

        // Перевіряємо збіг по площі (відхилення до 10%)
        double diff1 = Math.abs(r.getHave().getArea() - current.getWant().getArea());
        double diff2 = Math.abs(current.getHave().getArea() - r.getWant().getArea());

        boolean areaMatch =
                diff1 <= r.getHave().getArea() * 0.10 &&
                        diff2 <= current.getHave().getArea() * 0.10;

        // Якщо підходить - додаємо в кошик знайдених
        if (basicMatch && areaMatch) {
            foundMatches.add(r);
        }
    }
// ...

    public void acceptExchange(Long id1, Long id2)
    { repository.deleteById(id1); repository.deleteById(id2); }


    boolean basicMatch = r.getHave().getRooms() == current.getWant().getRooms() && r.getWant().getRooms() == current.getHave().getRooms() && r.getHave().getDistrict().equalsIgnoreCase(current.getWant().getDistrict()) && r.getWant().getDistrict().equalsIgnoreCase(current.getHave().getDistrict());

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
