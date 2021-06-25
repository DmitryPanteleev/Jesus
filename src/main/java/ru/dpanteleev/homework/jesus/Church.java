package ru.dpanteleev.homework.jesus;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.dpanteleev.homework.jesus.domain.Water;
import ru.dpanteleev.homework.jesus.domain.Wine;


@MessagingGateway
public interface Church {

    @Gateway(requestChannel = "waterChannel", replyChannel = "wineChannel")
    Wine process(Water water);
}
