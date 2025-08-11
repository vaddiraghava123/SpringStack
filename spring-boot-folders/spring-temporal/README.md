### Spring Temporal

Temporal.io

> Auto retires Timeout handling Long-running workflows Failure recovery
> State Management

Workflow -\> Activities

> Spring Boot application -\> Workflow Client \| Start workflow \| (gRPC)
> Temporal server \[ Retry , Rollback \] \| History Service \[ Writes
> workflow metadata & history events \] \| Matching Service \[ Places task
> in Task Queue 1. Workflow 2. Activities \] \| TRAVEL_TASK_QUEUE \|
> WORKER , WORKER \[ TravelBooking Worker \]

### Execution in local env

 > First time run docker-compose 

1.  Command to run the docker-compose file in command prompt \>
    > docker-compose up build

    Second time run the containers directly in Docker desktop
    > <img width="1306" height="321" alt="image" src="https://github.com/user-attachments/assets/8787e8d5-e01e-4d80-826e-bf32c6044f1d" />


  > http://localhost:9191/swagger-ui/index.html
  > <img width="1113" height="657" alt="image" src="https://github.com/user-attachments/assets/5f584f62-4afb-4002-bc65-9806f4313be9" />

2. Temporal UI http://localhost:8088/
   ><img width="1584" height="682" alt="image" src="https://github.com/user-attachments/assets/7527f1e1-b871-4175-ac1b-10ab0fe5c83f" />

   


