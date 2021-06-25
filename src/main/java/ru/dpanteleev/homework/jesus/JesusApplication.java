package ru.dpanteleev.homework.jesus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.dpanteleev.homework.jesus.domain.Water;
import ru.dpanteleev.homework.jesus.domain.Wine;

@IntegrationComponentScan
@ComponentScan
@Configuration
@EnableIntegration
public class JesusApplication {

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(JesusApplication.class, args);
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(JesusApplication.class);
        Church church = ctx.getBean(Church.class);
        while (true) {
            Thread.sleep(1000);
            Water water = new Water("Святая вода");
            System.out.println("Создали святую воду");
            Wine wine = church.process(water);
            System.out.println("Получили вино " + wine.getName());
        }
    }

    @Bean
    public QueueChannel waterChannel() {
        return MessageChannels.queue(1).get();
    }

    @Bean
    public PublishSubscribeChannel wineChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow creatFlow() {
        return IntegrationFlows.from("waterChannel")
                .handle("jesusService", "getWineFromWater")
                .channel("wineChannel")
                .get();
    }
}
