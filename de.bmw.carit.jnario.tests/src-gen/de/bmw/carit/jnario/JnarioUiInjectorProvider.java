/*
* generated by Xtext
*/
package de.bmw.carit.jnario;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class JnarioUiInjectorProvider implements IInjectorProvider {
	
	public Injector getInjector() {
		return de.bmw.carit.jnario.ui.internal.JnarioActivator.getInstance().getInjector("de.bmw.carit.jnario.Jnario");
	}
	
}
