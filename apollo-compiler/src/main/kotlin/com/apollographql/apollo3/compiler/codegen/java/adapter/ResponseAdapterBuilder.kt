package com.apollographql.apollo3.compiler.codegen.java.adapter

import com.apollographql.apollo3.compiler.codegen.java.JavaContext
import com.apollographql.apollo3.compiler.ir.IrModelGroup
import com.squareup.javapoet.TypeSpec


interface ResponseAdapterBuilder {

  fun prepare()

  fun build(): List<TypeSpec>

  companion object {
    fun create(
        context: JavaContext,
        modelGroup: IrModelGroup,
        path: List<String>,
        public: Boolean
    ): ResponseAdapterBuilder = when(modelGroup.models.size) {
      0 -> error("Don't know how to create an adapter for a scalar type")
      1 -> MonomorphicFieldResponseAdapterBuilder(
          context = context,
          model = modelGroup.models.first(),
          path = path,
          public = public
      )
      else -> error("Don't know how to create an adapter for a polymorphic type")
    }
  }
}
