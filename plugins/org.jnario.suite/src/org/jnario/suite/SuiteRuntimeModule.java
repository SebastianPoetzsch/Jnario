/*******************************************************************************
 * Copyright (c) 2012 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
/*
 * generated by Xtext
 */
package org.jnario.suite;

import org.eclipse.xtend.core.compiler.XtendCompiler;
import org.eclipse.xtend.core.compiler.XtendOutputConfigurationProvider;
import org.eclipse.xtend.core.conversion.JavaIDValueConverter;
import org.eclipse.xtend.core.formatting.XtendFormatter;
import org.eclipse.xtend.core.imports.XtendImportsConfiguration;
import org.eclipse.xtend.core.jvmmodel.IXtendJvmAssociations;
import org.eclipse.xtend.core.linking.XtendLinkingDiagnosticMessageProvider;
import org.eclipse.xtend.core.resource.XtendLocationInFileProvider;
import org.eclipse.xtend.core.typesystem.DispatchAndExtensionAwareReentrantTypeResolver;
import org.eclipse.xtend.core.typesystem.TypeDeclarationAwareBatchTypeResolver;
import org.eclipse.xtend.core.typesystem.XtendTypeComputer;
import org.eclipse.xtend.core.typing.XtendExpressionHelper;
import org.eclipse.xtend.core.validation.XtendConfigurableIssueCodes;
import org.eclipse.xtend.core.validation.XtendEarlyExitValidator;
import org.eclipse.xtend.core.xtend.XtendFactory;
import org.eclipse.xtend.lib.macro.file.FileLocations;
import org.eclipse.xtend.lib.macro.file.MutableFileSystemSupport;
import org.eclipse.xtext.common.types.descriptions.JvmDeclaredTypeSignatureHashProvider.SignatureHashBuilder;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.conversion.impl.IDValueConverter;
import org.eclipse.xtext.generator.IFilePostProcessor;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfigurationProvider;
import org.eclipse.xtext.linking.ILinkingDiagnosticMessageProvider;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.containers.IAllContainersState.Provider;
import org.eclipse.xtext.resource.impl.EagerResourceSetBasedResourceDescriptions;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.validation.CompositeEValidator;
import org.eclipse.xtext.validation.ConfigurableIssueCodesProvider;
import org.eclipse.xtext.xbase.XbaseFactory;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.TraceAwarePostProcessor;
import org.eclipse.xtext.xbase.file.FileLocationsImpl;
import org.eclipse.xtext.xbase.file.JavaIOFileSystemSupport;
import org.eclipse.xtext.xbase.file.RuntimeWorkspaceConfigProvider;
import org.eclipse.xtext.xbase.file.WorkspaceConfig;
import org.eclipse.xtext.xbase.formatting.IBasicFormatter;
import org.eclipse.xtext.xbase.imports.IImportsConfiguration;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputer;
import org.eclipse.xtext.xbase.typesystem.internal.DefaultBatchTypeResolver;
import org.eclipse.xtext.xbase.typesystem.internal.DefaultReentrantTypeResolver;
import org.eclipse.xtext.xbase.util.XExpressionHelper;
import org.eclipse.xtext.xbase.validation.EarlyExitValidator;
import org.jnario.doc.AbstractDocGenerator;
import org.jnario.doc.DocOutputConfigurationProvider;
import org.jnario.generator.JnarioJavaIoFileSystemAccess;
import org.jnario.jvmmodel.ExecutableProvider;
import org.jnario.jvmmodel.ExtendedJvmModelGenerator;
import org.jnario.jvmmodel.ExtendedJvmTypesBuilder;
import org.jnario.jvmmodel.JnarioNameProvider;
import org.jnario.jvmmodel.JnarioSignatureHashBuilder;
import org.jnario.report.Executable2ResultMapping;
import org.jnario.report.HashBasedSpec2ResultMapping;
import org.jnario.scoping.EagerResourceSetBasedAllContainersStateProvider;
import org.jnario.scoping.JnarioResourceDescriptionStrategy;
import org.jnario.suite.compiler.SuiteBatchCompiler;
import org.jnario.suite.conversion.SuiteValueConverterService;
import org.jnario.suite.doc.SuiteDocGenerator;
import org.jnario.suite.generator.SuiteGenerator;
import org.jnario.suite.jvmmodel.SuiteClassNameProvider;
import org.jnario.suite.jvmmodel.SuiteExecutableProvider;
import org.jnario.suite.jvmmodel.SuiteJvmModelInferrer;
import org.jnario.suite.naming.SuiteQualifiedNameProvider;
import org.jnario.suite.resource.SuiteResourceDescriptionManager;
import org.jnario.suite.scoping.SuiteImportedNamespaceScopeProvider;
import org.jnario.suite.scoping.SuiteScopeProvider;

import com.google.inject.Binder;
import com.google.inject.name.Names;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
@SuppressWarnings("restriction")
public class SuiteRuntimeModule extends org.jnario.suite.AbstractSuiteRuntimeModule {

	@Override
	public void configure(Binder binder) {
		super.configure(binder);
		binder.bind(AbstractDocGenerator.class).to(SuiteDocGenerator.class);
		binder.bind(SignatureHashBuilder.class).to(JnarioSignatureHashBuilder.class);
		binder.bind(JnarioNameProvider.class).to(SuiteClassNameProvider.class);
		binder.bind(ExecutableProvider.class).to(SuiteExecutableProvider.class);
		binder.bind(Executable2ResultMapping.class).to(HashBasedSpec2ResultMapping.class);
		binder.bind(boolean.class).annotatedWith(
				Names.named(CompositeEValidator.USE_EOBJECT_VALIDATOR)).toInstance(false);
	}
	
	@Override
	public Class<? extends IValueConverterService> bindIValueConverterService() {
		return SuiteValueConverterService.class;
	}
	
	public Class<? extends org.eclipse.xtext.generator.IGenerator> bindIGenerator() {
		return SuiteGenerator.class;
	}

	public Class<? extends OutputConfigurationProvider> bindOutputConfigurationProvider() {
		return DocOutputConfigurationProvider.class;
	}
	
	public Class<? extends ILinkingDiagnosticMessageProvider> bindILinkingDiagnosticMessageProvider() {
		return XtendLinkingDiagnosticMessageProvider.class;
	}
	
	public Class<? extends JvmTypesBuilder> bindJvmTypesBuilder(){
		return ExtendedJvmTypesBuilder.class;
	}
	
	public Class<? extends JvmModelGenerator> bindJvmModelGenerator(){
		return ExtendedJvmModelGenerator.class;
	}
	
	@Override
	public java.lang.Class<? extends IScopeProvider> bindIScopeProvider() {
		return SuiteScopeProvider.class;
	}

	public void configureIScopeProviderDelegate(Binder binder) {
		binder.bind(IScopeProvider.class).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE))
		.to(SuiteImportedNamespaceScopeProvider.class);
	}

	public Class <? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
		return JnarioResourceDescriptionStrategy.class;
	}

	public Class<? extends JvmModelAssociator> bindJvmModelAssociator() {
		return IXtendJvmAssociations.Impl.class;
	}

	public Class<? extends EarlyExitValidator> bindEarlyExitValidator() {
		return XtendEarlyExitValidator.class;
	}
	
	public Class<? extends IFilePostProcessor> bindPostProcessor() {
		return TraceAwarePostProcessor.class;
	}
	
	public Class<? extends JavaIoFileSystemAccess> bindJavaIoFileSystemAccess() {
		return JnarioJavaIoFileSystemAccess.class;
	}
	
	public Class<? extends IJvmModelInferrer> bindIJvmModelInferrer() {
		return SuiteJvmModelInferrer.class;
	}
	
	@Override
	public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
		return SuiteQualifiedNameProvider.class;
	}

	@Override
	public Class<? extends IResourceDescription.Manager> bindIResourceDescription$Manager() {
		return SuiteResourceDescriptionManager.class;
	}
	
	public Class<? extends org.jnario.compiler.JnarioBatchCompiler> bindJnarioBatchCompiler(){
		return SuiteBatchCompiler.class;
	}
	
	public XbaseFactory bindXbaseFactory() {
		return XbaseFactory.eINSTANCE;
	}
	
	public Class<? extends XExpressionHelper> bindXExpressionHelper() {
		return XtendExpressionHelper.class;
	}
	
	public Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
		return XtendOutputConfigurationProvider.class;
	}
	
	@Override
	public Class<? extends ILocationInFileProvider> bindILocationInFileProvider() {
		return XtendLocationInFileProvider.class;
	}

	public Class<? extends IBasicFormatter> bindIBasicFormatter() {
		return XtendFormatter.class;
	}

	public Class<? extends IImportsConfiguration> bindIImportsConfiguration() {
		return XtendImportsConfiguration.class;
	}

	@Override
	public Class<? extends ConfigurableIssueCodesProvider> bindConfigurableIssueCodesProvider() {
		return XtendConfigurableIssueCodes.class;
	}
	
	public XtendFactory bindXtendFactory() {
		return XtendFactory.eINSTANCE;
	}

	@Override
	public Class<? extends DefaultBatchTypeResolver> bindDefaultBatchTypeResolver() {
		return TypeDeclarationAwareBatchTypeResolver.class;
	}

	@Override
	public Class<? extends DefaultReentrantTypeResolver> bindDefaultReentrantTypeResolver() {
		return DispatchAndExtensionAwareReentrantTypeResolver.class;
	}
	
	public Class<? extends XbaseCompiler> bindXbaseCompiler() {
		return XtendCompiler.class;
	}
	
	@Override
	public Class<? extends ITypeComputer> bindITypeComputer() {
		return XtendTypeComputer.class;
	}
	
	@Override
	public Class<? extends Provider> bindIAllContainersState$Provider() {
		return EagerResourceSetBasedAllContainersStateProvider.class;
	}
	
	@Override
	public void configureIResourceDescriptions(com.google.inject.Binder binder) {
		binder.bind(IResourceDescriptions.class).to(EagerResourceSetBasedResourceDescriptions.class);
	}
	
	public Class<? extends MutableFileSystemSupport> bindFileHandleFactory() {
		return JavaIOFileSystemSupport.class;
	}
	
	public void configureWorkspaceConfigContribution(Binder binder) {
		binder.bind(WorkspaceConfig.class).toProvider(RuntimeWorkspaceConfigProvider.class);
	}
	
	public Class<? extends FileLocations> bindFileLocations() {
		return FileLocationsImpl.class;
	}
	
	public Class<? extends IDValueConverter> bindIDValueConverter() {
		return JavaIDValueConverter.class;
	}
}
