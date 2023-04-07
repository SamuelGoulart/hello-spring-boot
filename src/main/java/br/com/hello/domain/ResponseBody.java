package br.com.hello.domain;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseBody {
	public String menssage;
    public Object payload;
    public ArrayList<?> error;
}
