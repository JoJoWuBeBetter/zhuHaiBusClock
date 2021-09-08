package com.jojo.zhuhaibusclock.model.params;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author JoJoWu
 */
@Data
@ToString
public class BarkUrlParam {
    @NotNull(message = "userId不能为空")
    private Long userId;

    @NotNull(message = "网址不能为空")
    private String barkUrl;
}
