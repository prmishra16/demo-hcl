package start.myprojectbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private LocalDate createTimeStamp ;
    private LocalDate orderDate;
    private Long orderRefNo;
    private Long portfolioId;
    private Long AssetId;
    private String orderStatus;
    private Double orderPrice ;
    private Integer orderQty;
    private LocalDate modifyTimeStamp;
}
