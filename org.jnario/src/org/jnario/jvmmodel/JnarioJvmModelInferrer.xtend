/*******************************************************************************
 * Copyright (c) 2012 BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.jnario.jvmmodel

import static com.google.common.base.Predicates.*
import static extension com.google.common.collect.Iterables.*

import com.google.inject.Inject
import org.jnario.ExampleColumn
import org.jnario.ExampleRow
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.common.types.util.TypeConformanceComputer
import org.eclipse.xtext.common.types.util.TypeReferences
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.xtext.xbase.compiler.StringBuilderBasedAppendable
import org.eclipse.xtext.xbase.compiler.XbaseCompiler
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import org.eclipse.xtext.xbase.typing.ITypeProvider
import org.eclipse.xtext.xtend2.jvmmodel.Xtend2JvmModelInferrer
import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import org.jnario.runner.Named
import org.eclipse.emf.ecore.EObject

/**
 * @author Birgit Engelmann
 * @author Sebastian Benz
 */
class JnarioJvmModelInferrer extends Xtend2JvmModelInferrer {

	@Inject public XbaseCompiler compiler
	@Inject extension ITypeProvider
	@Inject extension TypeConformanceComputer
	@Inject extension TypeReferences
	@Inject extension JvmTypesBuilder
	
	def checkClassPath(EObject context, JunitAnnotationProvider annotationProvider){
		try{
			annotationProvider.getBeforeAnnotation(context)
		}catch(RuntimeException e){
			return false
		}
		if (typeof(Named).findDeclaredType(context) == null) {
			return false;
		}
		return true
	}
	
	def toField(ExampleColumn column){
		val field = column.toField(column.name, column.getOrCreateType)
		field.visibility = JvmVisibility::PUBLIC
		return field
	}
	
	def getOrCreateType(ExampleColumn column){
		if(column.type == null || column.type.type == null){
			if(column.cells.empty){
				column.type = getTypeForName(typeof(Object), column)
			}else{
				var cellTypes = column.cells.transform[type]
				cellTypes = Iterables::filter(cellTypes, notNull)
				val cellTypeList = Lists::newArrayList(cellTypes)
				if(cellTypeList.empty){
					column.type = getTypeForName(typeof(Object), column)
				}else{
					column.type = cellTypeList.commonSuperType 
				}
			}
		}
		return column.type
	}
	
	def cellToAppendable(ExampleRow row, int i){
		var appendable = new StringBuilderBasedAppendable()
		if(row.getCells.size > i){
			compiler.toJavaExpression(row.getCells.get(i), appendable)
		}
		appendable
	}
	
	def toJavaExpression(XExpression expr){
		var appendable = new StringBuilderBasedAppendable()
		compiler.toJavaExpression(expr, appendable)
		appendable
	}
	
	def toJavaStatement(XExpression expr, boolean isReferenced){
		var appendable = new StringBuilderBasedAppendable()
		compiler.toJavaStatement(expr, appendable, isReferenced)
		appendable
	}

}