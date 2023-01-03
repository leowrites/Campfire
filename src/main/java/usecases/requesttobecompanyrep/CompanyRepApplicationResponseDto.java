package usecases.requesttobecompanyrep;

import service.ServerStatus;

public class CompanyRepApplicationResponseDto {
    ServerStatus serverStatus;
    String message;
    public CompanyRepApplicationResponseDto(ServerStatus serverStatus, String message) {
        this.message = message;
        this.serverStatus = serverStatus;
    }


}
