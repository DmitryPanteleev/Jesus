package ru.dpanteleev.homework.jesus.panteon;

import org.springframework.stereotype.Service;
import ru.dpanteleev.homework.jesus.domain.Water;
import ru.dpanteleev.homework.jesus.domain.Wine;

@Service
public class JesusService {

    public Wine getWineFromWater(Water water) throws Exception {
        System.out.println("Молимся за успех сего предприятия");
        Thread.sleep(3000);
        System.out.println("Молитва услышена");
        return new Wine(water.getName() + " transform to Wine");
    }
}
