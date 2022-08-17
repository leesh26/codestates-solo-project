package api.v1.dto.response;

import api.v1.dto.response.BasicResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SuccessResponse<T> extends BasicResponse {
    private int count;
    private T data;

    public SuccessResponse(T data){
        this.data = data;
        if (data instanceof List) {
            this.count = ((List<?>) data).size();
        }
        else this.count = 1;
    }
}
