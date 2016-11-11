package br.ufsc.bridge.res.service.dto.registry;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistryResponse<T> {

	private boolean status;
	private List<T> items = new ArrayList<>();

	public RegistryResponse(boolean status) {
		this.status = status;
	}
}
