package br.com.template.domain;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseBody {
	public String message;
    public Object payload;
    public ArrayList<?> error;
}
