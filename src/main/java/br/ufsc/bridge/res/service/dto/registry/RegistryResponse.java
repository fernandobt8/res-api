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

	private List<T> items = new ArrayList<>();

}
