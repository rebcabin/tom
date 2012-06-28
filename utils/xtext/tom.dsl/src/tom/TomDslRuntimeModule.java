/*
 * generated by Xtext
 */
package tom;

import org.eclipse.xtext.parser.antlr.Lexer;
import org.eclipse.xtext.parser.antlr.LexerBindings;

import tom.lexer.CustomTomMappingLexer;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class TomDslRuntimeModule extends tom.AbstractTomDslRuntimeModule {

	@Override
	public void configureRuntimeLexer(Binder binder) {
	    binder.bind(Lexer.class)
	                .annotatedWith(Names.named(LexerBindings.RUNTIME))
	                .to(CustomTomMappingLexer.class);
	}
}
