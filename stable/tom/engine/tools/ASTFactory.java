
























package tom.engine.tools;



import java.util.*;



import tom.engine.TomBase;
import tom.engine.adt.tomterm.*;



import tom.engine.adt.tomsignature.*;
import tom.engine.adt.tomconstraint.types.*;
import tom.engine.adt.tomdeclaration.types.*;
import tom.engine.adt.tomexpression.types.*;
import tom.engine.adt.tominstruction.types.*;
import tom.engine.adt.tomname.types.*;
import tom.engine.adt.tomoption.types.*;
import tom.engine.adt.tomsignature.types.*;
import tom.engine.adt.tomterm.types.*;
import tom.engine.adt.tomslot.types.*;
import tom.engine.adt.tomtype.types.*;
import tom.engine.adt.code.types.*;
import tom.engine.adt.code.types.bqterm.Composite;



import tom.engine.exception.TomRuntimeException;
import aterm.ATerm;



public class ASTFactory {
     private static   tom.engine.adt.tomsignature.types.ResolveStratBlockList  tom_append_list_concResolveStratBlock( tom.engine.adt.tomsignature.types.ResolveStratBlockList l1,  tom.engine.adt.tomsignature.types.ResolveStratBlockList  l2) {     if( l1.isEmptyconcResolveStratBlock() ) {       return l2;     } else if( l2.isEmptyconcResolveStratBlock() ) {       return l1;     } else if(  l1.getTailconcResolveStratBlock() .isEmptyconcResolveStratBlock() ) {       return  tom.engine.adt.tomsignature.types.resolvestratblocklist.ConsconcResolveStratBlock.make( l1.getHeadconcResolveStratBlock() ,l2) ;     } else {       return  tom.engine.adt.tomsignature.types.resolvestratblocklist.ConsconcResolveStratBlock.make( l1.getHeadconcResolveStratBlock() ,tom_append_list_concResolveStratBlock( l1.getTailconcResolveStratBlock() ,l2)) ;     }   }   private static   tom.engine.adt.tomsignature.types.ResolveStratBlockList  tom_get_slice_concResolveStratBlock( tom.engine.adt.tomsignature.types.ResolveStratBlockList  begin,  tom.engine.adt.tomsignature.types.ResolveStratBlockList  end, tom.engine.adt.tomsignature.types.ResolveStratBlockList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcResolveStratBlock()  ||  (end== tom.engine.adt.tomsignature.types.resolvestratblocklist.EmptyconcResolveStratBlock.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomsignature.types.resolvestratblocklist.ConsconcResolveStratBlock.make( begin.getHeadconcResolveStratBlock() ,( tom.engine.adt.tomsignature.types.ResolveStratBlockList )tom_get_slice_concResolveStratBlock( begin.getTailconcResolveStratBlock() ,end,tail)) ;   }      private static   tom.engine.adt.tomsignature.types.ElementaryTransformationList  tom_append_list_concElementaryTransformation( tom.engine.adt.tomsignature.types.ElementaryTransformationList l1,  tom.engine.adt.tomsignature.types.ElementaryTransformationList  l2) {     if( l1.isEmptyconcElementaryTransformation() ) {       return l2;     } else if( l2.isEmptyconcElementaryTransformation() ) {       return l1;     } else if(  l1.getTailconcElementaryTransformation() .isEmptyconcElementaryTransformation() ) {       return  tom.engine.adt.tomsignature.types.elementarytransformationlist.ConsconcElementaryTransformation.make( l1.getHeadconcElementaryTransformation() ,l2) ;     } else {       return  tom.engine.adt.tomsignature.types.elementarytransformationlist.ConsconcElementaryTransformation.make( l1.getHeadconcElementaryTransformation() ,tom_append_list_concElementaryTransformation( l1.getTailconcElementaryTransformation() ,l2)) ;     }   }   private static   tom.engine.adt.tomsignature.types.ElementaryTransformationList  tom_get_slice_concElementaryTransformation( tom.engine.adt.tomsignature.types.ElementaryTransformationList  begin,  tom.engine.adt.tomsignature.types.ElementaryTransformationList  end, tom.engine.adt.tomsignature.types.ElementaryTransformationList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcElementaryTransformation()  ||  (end== tom.engine.adt.tomsignature.types.elementarytransformationlist.EmptyconcElementaryTransformation.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomsignature.types.elementarytransformationlist.ConsconcElementaryTransformation.make( begin.getHeadconcElementaryTransformation() ,( tom.engine.adt.tomsignature.types.ElementaryTransformationList )tom_get_slice_concElementaryTransformation( begin.getTailconcElementaryTransformation() ,end,tail)) ;   }      private static   tom.engine.adt.tomsignature.types.TomVisitList  tom_append_list_concTomVisit( tom.engine.adt.tomsignature.types.TomVisitList l1,  tom.engine.adt.tomsignature.types.TomVisitList  l2) {     if( l1.isEmptyconcTomVisit() ) {       return l2;     } else if( l2.isEmptyconcTomVisit() ) {       return l1;     } else if(  l1.getTailconcTomVisit() .isEmptyconcTomVisit() ) {       return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( l1.getHeadconcTomVisit() ,l2) ;     } else {       return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( l1.getHeadconcTomVisit() ,tom_append_list_concTomVisit( l1.getTailconcTomVisit() ,l2)) ;     }   }   private static   tom.engine.adt.tomsignature.types.TomVisitList  tom_get_slice_concTomVisit( tom.engine.adt.tomsignature.types.TomVisitList  begin,  tom.engine.adt.tomsignature.types.TomVisitList  end, tom.engine.adt.tomsignature.types.TomVisitList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomVisit()  ||  (end== tom.engine.adt.tomsignature.types.tomvisitlist.EmptyconcTomVisit.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make( begin.getHeadconcTomVisit() ,( tom.engine.adt.tomsignature.types.TomVisitList )tom_get_slice_concTomVisit( begin.getTailconcTomVisit() ,end,tail)) ;   }      private static   tom.engine.adt.tomsignature.types.ResolveStratElementList  tom_append_list_concResolveStratElement( tom.engine.adt.tomsignature.types.ResolveStratElementList l1,  tom.engine.adt.tomsignature.types.ResolveStratElementList  l2) {     if( l1.isEmptyconcResolveStratElement() ) {       return l2;     } else if( l2.isEmptyconcResolveStratElement() ) {       return l1;     } else if(  l1.getTailconcResolveStratElement() .isEmptyconcResolveStratElement() ) {       return  tom.engine.adt.tomsignature.types.resolvestratelementlist.ConsconcResolveStratElement.make( l1.getHeadconcResolveStratElement() ,l2) ;     } else {       return  tom.engine.adt.tomsignature.types.resolvestratelementlist.ConsconcResolveStratElement.make( l1.getHeadconcResolveStratElement() ,tom_append_list_concResolveStratElement( l1.getTailconcResolveStratElement() ,l2)) ;     }   }   private static   tom.engine.adt.tomsignature.types.ResolveStratElementList  tom_get_slice_concResolveStratElement( tom.engine.adt.tomsignature.types.ResolveStratElementList  begin,  tom.engine.adt.tomsignature.types.ResolveStratElementList  end, tom.engine.adt.tomsignature.types.ResolveStratElementList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcResolveStratElement()  ||  (end== tom.engine.adt.tomsignature.types.resolvestratelementlist.EmptyconcResolveStratElement.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomsignature.types.resolvestratelementlist.ConsconcResolveStratElement.make( begin.getHeadconcResolveStratElement() ,( tom.engine.adt.tomsignature.types.ResolveStratElementList )tom_get_slice_concResolveStratElement( begin.getTailconcResolveStratElement() ,end,tail)) ;   }      private static   tom.engine.adt.tomterm.types.TomList  tom_append_list_concTomTerm( tom.engine.adt.tomterm.types.TomList l1,  tom.engine.adt.tomterm.types.TomList  l2) {     if( l1.isEmptyconcTomTerm() ) {       return l2;     } else if( l2.isEmptyconcTomTerm() ) {       return l1;     } else if(  l1.getTailconcTomTerm() .isEmptyconcTomTerm() ) {       return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,l2) ;     } else {       return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( l1.getHeadconcTomTerm() ,tom_append_list_concTomTerm( l1.getTailconcTomTerm() ,l2)) ;     }   }   private static   tom.engine.adt.tomterm.types.TomList  tom_get_slice_concTomTerm( tom.engine.adt.tomterm.types.TomList  begin,  tom.engine.adt.tomterm.types.TomList  end, tom.engine.adt.tomterm.types.TomList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomTerm()  ||  (end== tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make( begin.getHeadconcTomTerm() ,( tom.engine.adt.tomterm.types.TomList )tom_get_slice_concTomTerm( begin.getTailconcTomTerm() ,end,tail)) ;   }      private static   tom.engine.adt.tomdeclaration.types.DeclarationList  tom_append_list_concDeclaration( tom.engine.adt.tomdeclaration.types.DeclarationList l1,  tom.engine.adt.tomdeclaration.types.DeclarationList  l2) {     if( l1.isEmptyconcDeclaration() ) {       return l2;     } else if( l2.isEmptyconcDeclaration() ) {       return l1;     } else if(  l1.getTailconcDeclaration() .isEmptyconcDeclaration() ) {       return  tom.engine.adt.tomdeclaration.types.declarationlist.ConsconcDeclaration.make( l1.getHeadconcDeclaration() ,l2) ;     } else {       return  tom.engine.adt.tomdeclaration.types.declarationlist.ConsconcDeclaration.make( l1.getHeadconcDeclaration() ,tom_append_list_concDeclaration( l1.getTailconcDeclaration() ,l2)) ;     }   }   private static   tom.engine.adt.tomdeclaration.types.DeclarationList  tom_get_slice_concDeclaration( tom.engine.adt.tomdeclaration.types.DeclarationList  begin,  tom.engine.adt.tomdeclaration.types.DeclarationList  end, tom.engine.adt.tomdeclaration.types.DeclarationList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcDeclaration()  ||  (end== tom.engine.adt.tomdeclaration.types.declarationlist.EmptyconcDeclaration.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomdeclaration.types.declarationlist.ConsconcDeclaration.make( begin.getHeadconcDeclaration() ,( tom.engine.adt.tomdeclaration.types.DeclarationList )tom_get_slice_concDeclaration( begin.getTailconcDeclaration() ,end,tail)) ;   }      private static   tom.engine.adt.tomtype.types.TomTypeList  tom_append_list_concTomType( tom.engine.adt.tomtype.types.TomTypeList l1,  tom.engine.adt.tomtype.types.TomTypeList  l2) {     if( l1.isEmptyconcTomType() ) {       return l2;     } else if( l2.isEmptyconcTomType() ) {       return l1;     } else if(  l1.getTailconcTomType() .isEmptyconcTomType() ) {       return  tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType.make( l1.getHeadconcTomType() ,l2) ;     } else {       return  tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType.make( l1.getHeadconcTomType() ,tom_append_list_concTomType( l1.getTailconcTomType() ,l2)) ;     }   }   private static   tom.engine.adt.tomtype.types.TomTypeList  tom_get_slice_concTomType( tom.engine.adt.tomtype.types.TomTypeList  begin,  tom.engine.adt.tomtype.types.TomTypeList  end, tom.engine.adt.tomtype.types.TomTypeList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomType()  ||  (end== tom.engine.adt.tomtype.types.tomtypelist.EmptyconcTomType.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomtype.types.tomtypelist.ConsconcTomType.make( begin.getHeadconcTomType() ,( tom.engine.adt.tomtype.types.TomTypeList )tom_get_slice_concTomType( begin.getTailconcTomType() ,end,tail)) ;   }      private static   tom.engine.adt.tomtype.types.TypeOptionList  tom_append_list_concTypeOption( tom.engine.adt.tomtype.types.TypeOptionList l1,  tom.engine.adt.tomtype.types.TypeOptionList  l2) {     if( l1.isEmptyconcTypeOption() ) {       return l2;     } else if( l2.isEmptyconcTypeOption() ) {       return l1;     } else if(  l1.getTailconcTypeOption() .isEmptyconcTypeOption() ) {       return  tom.engine.adt.tomtype.types.typeoptionlist.ConsconcTypeOption.make( l1.getHeadconcTypeOption() ,l2) ;     } else {       return  tom.engine.adt.tomtype.types.typeoptionlist.ConsconcTypeOption.make( l1.getHeadconcTypeOption() ,tom_append_list_concTypeOption( l1.getTailconcTypeOption() ,l2)) ;     }   }   private static   tom.engine.adt.tomtype.types.TypeOptionList  tom_get_slice_concTypeOption( tom.engine.adt.tomtype.types.TypeOptionList  begin,  tom.engine.adt.tomtype.types.TypeOptionList  end, tom.engine.adt.tomtype.types.TypeOptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTypeOption()  ||  (end== tom.engine.adt.tomtype.types.typeoptionlist.EmptyconcTypeOption.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomtype.types.typeoptionlist.ConsconcTypeOption.make( begin.getHeadconcTypeOption() ,( tom.engine.adt.tomtype.types.TypeOptionList )tom_get_slice_concTypeOption( begin.getTailconcTypeOption() ,end,tail)) ;   }      private static   tom.engine.adt.code.types.CodeList  tom_append_list_concCode( tom.engine.adt.code.types.CodeList l1,  tom.engine.adt.code.types.CodeList  l2) {     if( l1.isEmptyconcCode() ) {       return l2;     } else if( l2.isEmptyconcCode() ) {       return l1;     } else if(  l1.getTailconcCode() .isEmptyconcCode() ) {       return  tom.engine.adt.code.types.codelist.ConsconcCode.make( l1.getHeadconcCode() ,l2) ;     } else {       return  tom.engine.adt.code.types.codelist.ConsconcCode.make( l1.getHeadconcCode() ,tom_append_list_concCode( l1.getTailconcCode() ,l2)) ;     }   }   private static   tom.engine.adt.code.types.CodeList  tom_get_slice_concCode( tom.engine.adt.code.types.CodeList  begin,  tom.engine.adt.code.types.CodeList  end, tom.engine.adt.code.types.CodeList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcCode()  ||  (end== tom.engine.adt.code.types.codelist.EmptyconcCode.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.code.types.codelist.ConsconcCode.make( begin.getHeadconcCode() ,( tom.engine.adt.code.types.CodeList )tom_get_slice_concCode( begin.getTailconcCode() ,end,tail)) ;   }      private static   tom.engine.adt.code.types.BQTermList  tom_append_list_concBQTerm( tom.engine.adt.code.types.BQTermList l1,  tom.engine.adt.code.types.BQTermList  l2) {     if( l1.isEmptyconcBQTerm() ) {       return l2;     } else if( l2.isEmptyconcBQTerm() ) {       return l1;     } else if(  l1.getTailconcBQTerm() .isEmptyconcBQTerm() ) {       return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( l1.getHeadconcBQTerm() ,l2) ;     } else {       return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( l1.getHeadconcBQTerm() ,tom_append_list_concBQTerm( l1.getTailconcBQTerm() ,l2)) ;     }   }   private static   tom.engine.adt.code.types.BQTermList  tom_get_slice_concBQTerm( tom.engine.adt.code.types.BQTermList  begin,  tom.engine.adt.code.types.BQTermList  end, tom.engine.adt.code.types.BQTermList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcBQTerm()  ||  (end== tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make( begin.getHeadconcBQTerm() ,( tom.engine.adt.code.types.BQTermList )tom_get_slice_concBQTerm( begin.getTailconcBQTerm() ,end,tail)) ;   }      private static   tom.engine.adt.code.types.BQTerm  tom_append_list_Composite( tom.engine.adt.code.types.BQTerm l1,  tom.engine.adt.code.types.BQTerm  l2) {     if( l1.isEmptyComposite() ) {       return l2;     } else if( l2.isEmptyComposite() ) {       return l1;     } else if(  l1.getTailComposite() .isEmptyComposite() ) {       return  tom.engine.adt.code.types.bqterm.ConsComposite.make( l1.getHeadComposite() ,l2) ;     } else {       return  tom.engine.adt.code.types.bqterm.ConsComposite.make( l1.getHeadComposite() ,tom_append_list_Composite( l1.getTailComposite() ,l2)) ;     }   }   private static   tom.engine.adt.code.types.BQTerm  tom_get_slice_Composite( tom.engine.adt.code.types.BQTerm  begin,  tom.engine.adt.code.types.BQTerm  end, tom.engine.adt.code.types.BQTerm  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyComposite()  ||  (end== tom.engine.adt.code.types.bqterm.EmptyComposite.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.code.types.bqterm.ConsComposite.make( begin.getHeadComposite() ,( tom.engine.adt.code.types.BQTerm )tom_get_slice_Composite( begin.getTailComposite() ,end,tail)) ;   }      private static   tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  tom_append_list_concRefClassTracelinkInstruction( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList l1,  tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  l2) {     if( l1.isEmptyconcRefClassTracelinkInstruction() ) {       return l2;     } else if( l2.isEmptyconcRefClassTracelinkInstruction() ) {       return l1;     } else if(  l1.getTailconcRefClassTracelinkInstruction() .isEmptyconcRefClassTracelinkInstruction() ) {       return  tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.ConsconcRefClassTracelinkInstruction.make( l1.getHeadconcRefClassTracelinkInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.ConsconcRefClassTracelinkInstruction.make( l1.getHeadconcRefClassTracelinkInstruction() ,tom_append_list_concRefClassTracelinkInstruction( l1.getTailconcRefClassTracelinkInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  tom_get_slice_concRefClassTracelinkInstruction( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  begin,  tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  end, tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcRefClassTracelinkInstruction()  ||  (end== tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.EmptyconcRefClassTracelinkInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.ConsconcRefClassTracelinkInstruction.make( begin.getHeadconcRefClassTracelinkInstruction() ,( tom.engine.adt.tominstruction.types.RefClassTracelinkInstructionList )tom_get_slice_concRefClassTracelinkInstruction( begin.getTailconcRefClassTracelinkInstruction() ,end,tail)) ;   }      private static   tom.engine.adt.tominstruction.types.RuleInstructionList  tom_append_list_concRuleInstruction( tom.engine.adt.tominstruction.types.RuleInstructionList l1,  tom.engine.adt.tominstruction.types.RuleInstructionList  l2) {     if( l1.isEmptyconcRuleInstruction() ) {       return l2;     } else if( l2.isEmptyconcRuleInstruction() ) {       return l1;     } else if(  l1.getTailconcRuleInstruction() .isEmptyconcRuleInstruction() ) {       return  tom.engine.adt.tominstruction.types.ruleinstructionlist.ConsconcRuleInstruction.make( l1.getHeadconcRuleInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.ruleinstructionlist.ConsconcRuleInstruction.make( l1.getHeadconcRuleInstruction() ,tom_append_list_concRuleInstruction( l1.getTailconcRuleInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.RuleInstructionList  tom_get_slice_concRuleInstruction( tom.engine.adt.tominstruction.types.RuleInstructionList  begin,  tom.engine.adt.tominstruction.types.RuleInstructionList  end, tom.engine.adt.tominstruction.types.RuleInstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcRuleInstruction()  ||  (end== tom.engine.adt.tominstruction.types.ruleinstructionlist.EmptyconcRuleInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.ruleinstructionlist.ConsconcRuleInstruction.make( begin.getHeadconcRuleInstruction() ,( tom.engine.adt.tominstruction.types.RuleInstructionList )tom_get_slice_concRuleInstruction( begin.getTailconcRuleInstruction() ,end,tail)) ;   }      private static   tom.engine.adt.tominstruction.types.InstructionList  tom_append_list_concInstruction( tom.engine.adt.tominstruction.types.InstructionList l1,  tom.engine.adt.tominstruction.types.InstructionList  l2) {     if( l1.isEmptyconcInstruction() ) {       return l2;     } else if( l2.isEmptyconcInstruction() ) {       return l1;     } else if(  l1.getTailconcInstruction() .isEmptyconcInstruction() ) {       return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( l1.getHeadconcInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( l1.getHeadconcInstruction() ,tom_append_list_concInstruction( l1.getTailconcInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.InstructionList  tom_get_slice_concInstruction( tom.engine.adt.tominstruction.types.InstructionList  begin,  tom.engine.adt.tominstruction.types.InstructionList  end, tom.engine.adt.tominstruction.types.InstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcInstruction()  ||  (end== tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( begin.getHeadconcInstruction() ,( tom.engine.adt.tominstruction.types.InstructionList )tom_get_slice_concInstruction( begin.getTailconcInstruction() ,end,tail)) ;   }      private static   tom.engine.adt.tominstruction.types.ConstraintInstructionList  tom_append_list_concConstraintInstruction( tom.engine.adt.tominstruction.types.ConstraintInstructionList l1,  tom.engine.adt.tominstruction.types.ConstraintInstructionList  l2) {     if( l1.isEmptyconcConstraintInstruction() ) {       return l2;     } else if( l2.isEmptyconcConstraintInstruction() ) {       return l1;     } else if(  l1.getTailconcConstraintInstruction() .isEmptyconcConstraintInstruction() ) {       return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( l1.getHeadconcConstraintInstruction() ,l2) ;     } else {       return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( l1.getHeadconcConstraintInstruction() ,tom_append_list_concConstraintInstruction( l1.getTailconcConstraintInstruction() ,l2)) ;     }   }   private static   tom.engine.adt.tominstruction.types.ConstraintInstructionList  tom_get_slice_concConstraintInstruction( tom.engine.adt.tominstruction.types.ConstraintInstructionList  begin,  tom.engine.adt.tominstruction.types.ConstraintInstructionList  end, tom.engine.adt.tominstruction.types.ConstraintInstructionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraintInstruction()  ||  (end== tom.engine.adt.tominstruction.types.constraintinstructionlist.EmptyconcConstraintInstruction.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make( begin.getHeadconcConstraintInstruction() ,( tom.engine.adt.tominstruction.types.ConstraintInstructionList )tom_get_slice_concConstraintInstruction( begin.getTailconcConstraintInstruction() ,end,tail)) ;   }      private static   tom.engine.adt.tomname.types.TomNameList  tom_append_list_concTomName( tom.engine.adt.tomname.types.TomNameList l1,  tom.engine.adt.tomname.types.TomNameList  l2) {     if( l1.isEmptyconcTomName() ) {       return l2;     } else if( l2.isEmptyconcTomName() ) {       return l1;     } else if(  l1.getTailconcTomName() .isEmptyconcTomName() ) {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,l2) ;     } else {       return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( l1.getHeadconcTomName() ,tom_append_list_concTomName( l1.getTailconcTomName() ,l2)) ;     }   }   private static   tom.engine.adt.tomname.types.TomNameList  tom_get_slice_concTomName( tom.engine.adt.tomname.types.TomNameList  begin,  tom.engine.adt.tomname.types.TomNameList  end, tom.engine.adt.tomname.types.TomNameList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcTomName()  ||  (end== tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make( begin.getHeadconcTomName() ,( tom.engine.adt.tomname.types.TomNameList )tom_get_slice_concTomName( begin.getTailconcTomName() ,end,tail)) ;   }      private static   tom.engine.adt.tomslot.types.BQSlotList  tom_append_list_concBQSlot( tom.engine.adt.tomslot.types.BQSlotList l1,  tom.engine.adt.tomslot.types.BQSlotList  l2) {     if( l1.isEmptyconcBQSlot() ) {       return l2;     } else if( l2.isEmptyconcBQSlot() ) {       return l1;     } else if(  l1.getTailconcBQSlot() .isEmptyconcBQSlot() ) {       return  tom.engine.adt.tomslot.types.bqslotlist.ConsconcBQSlot.make( l1.getHeadconcBQSlot() ,l2) ;     } else {       return  tom.engine.adt.tomslot.types.bqslotlist.ConsconcBQSlot.make( l1.getHeadconcBQSlot() ,tom_append_list_concBQSlot( l1.getTailconcBQSlot() ,l2)) ;     }   }   private static   tom.engine.adt.tomslot.types.BQSlotList  tom_get_slice_concBQSlot( tom.engine.adt.tomslot.types.BQSlotList  begin,  tom.engine.adt.tomslot.types.BQSlotList  end, tom.engine.adt.tomslot.types.BQSlotList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcBQSlot()  ||  (end== tom.engine.adt.tomslot.types.bqslotlist.EmptyconcBQSlot.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomslot.types.bqslotlist.ConsconcBQSlot.make( begin.getHeadconcBQSlot() ,( tom.engine.adt.tomslot.types.BQSlotList )tom_get_slice_concBQSlot( begin.getTailconcBQSlot() ,end,tail)) ;   }      private static   tom.engine.adt.tomslot.types.SlotList  tom_append_list_concSlot( tom.engine.adt.tomslot.types.SlotList l1,  tom.engine.adt.tomslot.types.SlotList  l2) {     if( l1.isEmptyconcSlot() ) {       return l2;     } else if( l2.isEmptyconcSlot() ) {       return l1;     } else if(  l1.getTailconcSlot() .isEmptyconcSlot() ) {       return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,l2) ;     } else {       return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( l1.getHeadconcSlot() ,tom_append_list_concSlot( l1.getTailconcSlot() ,l2)) ;     }   }   private static   tom.engine.adt.tomslot.types.SlotList  tom_get_slice_concSlot( tom.engine.adt.tomslot.types.SlotList  begin,  tom.engine.adt.tomslot.types.SlotList  end, tom.engine.adt.tomslot.types.SlotList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcSlot()  ||  (end== tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make( begin.getHeadconcSlot() ,( tom.engine.adt.tomslot.types.SlotList )tom_get_slice_concSlot( begin.getTailconcSlot() ,end,tail)) ;   }      private static   tom.engine.adt.tomslot.types.PairNameDeclList  tom_append_list_concPairNameDecl( tom.engine.adt.tomslot.types.PairNameDeclList l1,  tom.engine.adt.tomslot.types.PairNameDeclList  l2) {     if( l1.isEmptyconcPairNameDecl() ) {       return l2;     } else if( l2.isEmptyconcPairNameDecl() ) {       return l1;     } else if(  l1.getTailconcPairNameDecl() .isEmptyconcPairNameDecl() ) {       return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( l1.getHeadconcPairNameDecl() ,l2) ;     } else {       return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( l1.getHeadconcPairNameDecl() ,tom_append_list_concPairNameDecl( l1.getTailconcPairNameDecl() ,l2)) ;     }   }   private static   tom.engine.adt.tomslot.types.PairNameDeclList  tom_get_slice_concPairNameDecl( tom.engine.adt.tomslot.types.PairNameDeclList  begin,  tom.engine.adt.tomslot.types.PairNameDeclList  end, tom.engine.adt.tomslot.types.PairNameDeclList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcPairNameDecl()  ||  (end== tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make( begin.getHeadconcPairNameDecl() ,( tom.engine.adt.tomslot.types.PairNameDeclList )tom_get_slice_concPairNameDecl( begin.getTailconcPairNameDecl() ,end,tail)) ;   }      private static   tom.engine.adt.tomoption.types.OptionList  tom_append_list_concOption( tom.engine.adt.tomoption.types.OptionList l1,  tom.engine.adt.tomoption.types.OptionList  l2) {     if( l1.isEmptyconcOption() ) {       return l2;     } else if( l2.isEmptyconcOption() ) {       return l1;     } else if(  l1.getTailconcOption() .isEmptyconcOption() ) {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,l2) ;     } else {       return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( l1.getHeadconcOption() ,tom_append_list_concOption( l1.getTailconcOption() ,l2)) ;     }   }   private static   tom.engine.adt.tomoption.types.OptionList  tom_get_slice_concOption( tom.engine.adt.tomoption.types.OptionList  begin,  tom.engine.adt.tomoption.types.OptionList  end, tom.engine.adt.tomoption.types.OptionList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcOption()  ||  (end== tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( begin.getHeadconcOption() ,( tom.engine.adt.tomoption.types.OptionList )tom_get_slice_concOption( begin.getTailconcOption() ,end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyAndConstraint() ) {       return l2;     } else if( l2.isEmptyAndConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) ) {       if(  l1.getTailAndConstraint() .isEmptyAndConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( l1.getHeadAndConstraint() ,l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make( l1.getHeadAndConstraint() ,tom_append_list_AndConstraint( l1.getTailAndConstraint() ,l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_AndConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyAndConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getHeadAndConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_AndConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint)) )? begin.getTailAndConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ),end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.Constraint  tom_append_list_OrConstraint( tom.engine.adt.tomconstraint.types.Constraint  l1,  tom.engine.adt.tomconstraint.types.Constraint  l2) {     if( l1.isEmptyOrConstraint() ) {       return l2;     } else if( l2.isEmptyOrConstraint() ) {       return l1;     } else if( ((l1 instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (l1 instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) ) {       if(  l1.getTailOrConstraint() .isEmptyOrConstraint() ) {         return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make( l1.getHeadOrConstraint() ,l2) ;       } else {         return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make( l1.getHeadOrConstraint() ,tom_append_list_OrConstraint( l1.getTailOrConstraint() ,l2)) ;       }     } else {       return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make(l1,l2) ;     }   }   private static   tom.engine.adt.tomconstraint.types.Constraint  tom_get_slice_OrConstraint( tom.engine.adt.tomconstraint.types.Constraint  begin,  tom.engine.adt.tomconstraint.types.Constraint  end, tom.engine.adt.tomconstraint.types.Constraint  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyOrConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? begin.getHeadOrConstraint() :begin),( tom.engine.adt.tomconstraint.types.Constraint )tom_get_slice_OrConstraint((( ((begin instanceof tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint) || (begin instanceof tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint)) )? begin.getTailOrConstraint() : tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() ),end,tail)) ;   }      private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_append_list_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList l1,  tom.engine.adt.tomconstraint.types.ConstraintList  l2) {     if( l1.isEmptyconcConstraint() ) {       return l2;     } else if( l2.isEmptyconcConstraint() ) {       return l1;     } else if(  l1.getTailconcConstraint() .isEmptyconcConstraint() ) {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,l2) ;     } else {       return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( l1.getHeadconcConstraint() ,tom_append_list_concConstraint( l1.getTailconcConstraint() ,l2)) ;     }   }   private static   tom.engine.adt.tomconstraint.types.ConstraintList  tom_get_slice_concConstraint( tom.engine.adt.tomconstraint.types.ConstraintList  begin,  tom.engine.adt.tomconstraint.types.ConstraintList  end, tom.engine.adt.tomconstraint.types.ConstraintList  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyconcConstraint()  ||  (end== tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make( begin.getHeadconcConstraint() ,( tom.engine.adt.tomconstraint.types.ConstraintList )tom_get_slice_concConstraint( begin.getTailconcConstraint() ,end,tail)) ;   }   
   
  private ASTFactory() {}

  public static CodeList makeCodeList(Collection<Code> c) {
    CodeList list =  tom.engine.adt.code.types.codelist.EmptyconcCode.make() ;
    for(Code code: c) {
      list = tom_append_list_concCode(list, tom.engine.adt.code.types.codelist.ConsconcCode.make(code, tom.engine.adt.code.types.codelist.EmptyconcCode.make() ) );
    }
    return list;
  }

  public static BQTermList makeBQTermList(Collection<BQTerm> c) {
    BQTermList list =  tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ;
    for(BQTerm term: c) {
      list = tom_append_list_concBQTerm(list, tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm.make(term, tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm.make() ) );
    } 	     
    return list;
  }

  public static BQSlotList makeBQSlotList(Collection<BQSlot> c) {
    BQSlotList list =  tom.engine.adt.tomslot.types.bqslotlist.EmptyconcBQSlot.make() ;
    for(BQSlot slot: c) {
      list = tom_append_list_concBQSlot(list, tom.engine.adt.tomslot.types.bqslotlist.ConsconcBQSlot.make(slot, tom.engine.adt.tomslot.types.bqslotlist.EmptyconcBQSlot.make() ) );
    }
    return list;
  }

  public static Composite makeComposite(Collection<BQTerm> c) {
    BQTerm list =  tom.engine.adt.code.types.bqterm.EmptyComposite.make() ;
    for(BQTerm term: c) {
      list = tom_append_list_Composite(list, tom.engine.adt.code.types.bqterm.ConsComposite.make( tom.engine.adt.code.types.compositemember.CompositeBQTerm.make(term) , tom.engine.adt.code.types.bqterm.EmptyComposite.make() ) );
    } 	     
    return (Composite) list;
  }

  public static TomList makeTomList(Collection<TomTerm> c) {
    TomList list =  tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ;
    for(TomTerm term: c) {
      list = tom_append_list_concTomTerm(list, tom.engine.adt.tomterm.types.tomlist.ConsconcTomTerm.make(term, tom.engine.adt.tomterm.types.tomlist.EmptyconcTomTerm.make() ) );
    } 	     
    return list;
  }

  public static InstructionList makeInstructionList(Collection<Code> c) {
    InstructionList list =  tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ;
    for(Code code: c) {
      { /* unamed block */{ /* unamed block */if ( (code instanceof tom.engine.adt.code.types.Code) ) {if ( ((( tom.engine.adt.code.types.Code )code) instanceof tom.engine.adt.code.types.code.TargetLanguageToCode) ) {
 
          list = tom_append_list_concInstruction(list, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.CodeToInstruction.make( tom.engine.adt.code.types.code.TargetLanguageToCode.make( (( tom.engine.adt.code.types.Code )code).getTl() ) ) , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ); 
        }}}{ /* unamed block */if ( (code instanceof tom.engine.adt.code.types.Code) ) {if ( ((( tom.engine.adt.code.types.Code )code) instanceof tom.engine.adt.code.types.code.InstructionToCode) ) {
 
          list = tom_append_list_concInstruction(list, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( (( tom.engine.adt.code.types.Code )code).getAstInstruction() , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ); 
        }}}{ /* unamed block */if ( (code instanceof tom.engine.adt.code.types.Code) ) {if ( ((( tom.engine.adt.code.types.Code )code) instanceof tom.engine.adt.code.types.code.BQTermToCode) ) {
 
          list = tom_append_list_concInstruction(list, tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make( tom.engine.adt.tominstruction.types.instruction.BQTermToInstruction.make( (( tom.engine.adt.code.types.Code )code).getBq() ) , tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ) ); 
        }}}}

    }
    return list;
  }

  public static OptionList makeOptionList(List argumentList) {
    OptionList list =  tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      Object elt = argumentList.get(i);
      Option term;
      if(elt instanceof TomName) {
        term =  tom.engine.adt.tomoption.types.option.TomNameToOption.make((TomName)elt) ;
      } else if(elt instanceof Declaration) {
        term =  tom.engine.adt.tomoption.types.option.DeclarationToOption.make((Declaration)elt) ;
      } else if(elt instanceof TomTerm) {
        term =  tom.engine.adt.tomoption.types.option.TomTermToOption.make((TomTerm)elt) ;
      } else {
        term = (Option) elt;
      }
      list =  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make(term,tom_append_list_concOption(list, tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() )) ;
    }
    return list;
  }

  public static ConstraintList makeConstraintList(List<Constraint> argumentList) {
    ConstraintList list =  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make(argumentList.get(i),tom_append_list_concConstraint(list, tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() )) ;
    }
    return list;
  }

  public static ConstraintInstructionList makeConstraintInstructionList(List<ConstraintInstruction> argumentList) {
    ConstraintInstructionList list =  tom.engine.adt.tominstruction.types.constraintinstructionlist.EmptyconcConstraintInstruction.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tominstruction.types.constraintinstructionlist.ConsconcConstraintInstruction.make(argumentList.get(i),tom_append_list_concConstraintInstruction(list, tom.engine.adt.tominstruction.types.constraintinstructionlist.EmptyconcConstraintInstruction.make() )) ;
    }
    return list;
  }

  public static Constraint makeAndConstraint(List<Constraint> argumentList) {
    Constraint list =  tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomconstraint.types.constraint.ConsAndConstraint.make(argumentList.get(i),tom_append_list_AndConstraint(list, tom.engine.adt.tomconstraint.types.constraint.EmptyAndConstraint.make() )) ;
    }
    return list;
  }

  public static Constraint makeOrConstraint(List<Constraint> argumentList) {
    Constraint list =  tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomconstraint.types.constraint.ConsOrConstraint.make(argumentList.get(i),tom_append_list_OrConstraint(list, tom.engine.adt.tomconstraint.types.constraint.EmptyOrConstraint.make() )) ;
    }
    return list;
  }

  public static TomNameList makeNameList(List<TomName> argumentList) {
    TomNameList list =  tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomname.types.tomnamelist.ConsconcTomName.make(argumentList.get(i),tom_append_list_concTomName(list, tom.engine.adt.tomname.types.tomnamelist.EmptyconcTomName.make() )) ;
    }
    return list;
  }

  public static SlotList makeSlotList(List<Slot> argumentList) {
    SlotList list =  tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomslot.types.slotlist.ConsconcSlot.make(argumentList.get(i),tom_append_list_concSlot(list, tom.engine.adt.tomslot.types.slotlist.EmptyconcSlot.make() )) ;
    }
    return list;
  }

  public static PairNameDeclList makePairNameDeclList(List<PairNameDecl> argumentList) {
    PairNameDeclList list =  tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomslot.types.pairnamedecllist.ConsconcPairNameDecl.make(argumentList.get(i),tom_append_list_concPairNameDecl(list, tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl.make() )) ;
    }
    return list;
  }

  public static TomVisitList makeTomVisitList(List<TomVisit> argumentList) {
    TomVisitList list =  tom.engine.adt.tomsignature.types.tomvisitlist.EmptyconcTomVisit.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomsignature.types.tomvisitlist.ConsconcTomVisit.make(argumentList.get(i),tom_append_list_concTomVisit(list, tom.engine.adt.tomsignature.types.tomvisitlist.EmptyconcTomVisit.make() )) ;
    }
    return list;
  }

  
  public static ElementaryTransformationList makeElementaryTransformationList(List<ElementaryTransformation> argumentList) {
    ElementaryTransformationList list =  tom.engine.adt.tomsignature.types.elementarytransformationlist.EmptyconcElementaryTransformation.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomsignature.types.elementarytransformationlist.ConsconcElementaryTransformation.make(argumentList.get(i),tom_append_list_concElementaryTransformation(list, tom.engine.adt.tomsignature.types.elementarytransformationlist.EmptyconcElementaryTransformation.make() )) ;
    }
    return list;
  }

  public static RuleInstructionList makeRuleInstructionList(List<RuleInstruction> argumentList) {
    RuleInstructionList list =  tom.engine.adt.tominstruction.types.ruleinstructionlist.EmptyconcRuleInstruction.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tominstruction.types.ruleinstructionlist.ConsconcRuleInstruction.make(argumentList.get(i),tom_append_list_concRuleInstruction(list, tom.engine.adt.tominstruction.types.ruleinstructionlist.EmptyconcRuleInstruction.make() )) ;
    }
    return list;
  }

  public static InstructionList makeInstructionListFromInstructionCollection(List<Instruction> argumentList) {
    InstructionList list =  tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tominstruction.types.instructionlist.ConsconcInstruction.make(argumentList.get(i),tom_append_list_concInstruction(list, tom.engine.adt.tominstruction.types.instructionlist.EmptyconcInstruction.make() )) ;
    }
    return list;
  }

  public static RefClassTracelinkInstructionList makeRefClassTracelinkInstructionList(List<RefClassTracelinkInstruction> argumentList) {
    RefClassTracelinkInstructionList list =  tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.EmptyconcRefClassTracelinkInstruction.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.ConsconcRefClassTracelinkInstruction.make(argumentList.get(i),tom_append_list_concRefClassTracelinkInstruction(list, tom.engine.adt.tominstruction.types.refclasstracelinkinstructionlist.EmptyconcRefClassTracelinkInstruction.make() )) ;
    }
    return list;
  }

  public static ResolveStratElementList makeResolveStratElementList(List<ResolveStratElement> argumentList){
    ResolveStratElementList list =  tom.engine.adt.tomsignature.types.resolvestratelementlist.EmptyconcResolveStratElement.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomsignature.types.resolvestratelementlist.ConsconcResolveStratElement.make(argumentList.get(i),tom_append_list_concResolveStratElement(list, tom.engine.adt.tomsignature.types.resolvestratelementlist.EmptyconcResolveStratElement.make() )) ;
    }
    return list;
  }

  public static ResolveStratBlockList makeResolveStratBlockList(List<ResolveStratBlock> argumentList){
    ResolveStratBlockList list =  tom.engine.adt.tomsignature.types.resolvestratblocklist.EmptyconcResolveStratBlock.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomsignature.types.resolvestratblocklist.ConsconcResolveStratBlock.make(argumentList.get(i),tom_append_list_concResolveStratBlock(list, tom.engine.adt.tomsignature.types.resolvestratblocklist.EmptyconcResolveStratBlock.make() )) ;
    }
    return list;
  }

  public static DeclarationList makeDeclarationList(List<Declaration> argumentList){
    DeclarationList list =  tom.engine.adt.tomdeclaration.types.declarationlist.EmptyconcDeclaration.make() ;
    for(int i=argumentList.size()-1; i>=0 ; i--) {
      list =  tom.engine.adt.tomdeclaration.types.declarationlist.ConsconcDeclaration.make(argumentList.get(i),tom_append_list_concDeclaration(list, tom.engine.adt.tomdeclaration.types.declarationlist.EmptyconcDeclaration.make() )) ;
    }
    return list;
  }


  public static TomSymbol makeSymbol(String symbolName, TomType resultType, TomTypeList typeList,
      PairNameDeclList pairNameDeclList, List optionList) {
    return  tom.engine.adt.tomsignature.types.tomsymbol.Symbol.make( tom.engine.adt.tomname.types.tomname.Name.make(symbolName) ,  tom.engine.adt.tomtype.types.tomtype.TypesToType.make(typeList, resultType) , pairNameDeclList, makeOptionList(optionList)) ;
  }

  public static OptionList makeOption(Option arg) {
    OptionList list =  tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ;
    if(arg!= null) {
      list =  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make(arg,tom_append_list_concOption(list, tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() )) ;
    }
    return list;
  }

  public static ConstraintList makeConstraint(Constraint arg) {
    ConstraintList list =  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ;
    if(arg!= null) {
      list =  tom.engine.adt.tomconstraint.types.constraintlist.ConsconcConstraint.make(arg,tom_append_list_concConstraint(list, tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() )) ;
    }
    return list;
  }

  public static Constraint makeAliasTo(TomName name,int line, String fileName) {
    return  tom.engine.adt.tomconstraint.types.constraint.AliasTo.make( tom.engine.adt.tomterm.types.tomterm.Variable.make(makeOption(makeOriginTracking(name.getString(),line,fileName)), name, SymbolTable.TYPE_UNKNOWN,  tom.engine.adt.tomconstraint.types.constraintlist.EmptyconcConstraint.make() ) ) 


;
  }

  public static Constraint makeStorePosition(TomName name,int line, String fileName) {
    return  tom.engine.adt.tomconstraint.types.constraint.AssignPositionTo.make( tom.engine.adt.code.types.bqterm.BQVariable.make(makeOption(makeOriginTracking(name.getString(),line,fileName)), name, SymbolTable.TYPE_UNKNOWN) ) 

;
  }

  public static OptionList makeOption(Option arg, Option info) {
    OptionList list =  tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ;
    if(arg!= null) {
      list =  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make(arg,tom_append_list_concOption(list, tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() )) ;
    }
    list =  tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make(info,tom_append_list_concOption(list, tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() )) ;
    return list;
  }

  private static Option makeOriginTracking(String name, int line , String fileName) {
    return  tom.engine.adt.tomoption.types.option.OriginTracking.make( tom.engine.adt.tomname.types.tomname.Name.make(name) , line, fileName) ;
  }

  public static TomType makeType(TypeOptionList tOptionList, String typeNameTom, String typeNametGL) {
    return  tom.engine.adt.tomtype.types.tomtype.Type.make(tOptionList, typeNameTom,  tom.engine.adt.tomtype.types.targetlanguagetype.TLType.make(typeNametGL) ) ;
  }

    
  private static void makeSortSymbol(SymbolTable symbolTable,
                             String sort,
                             String value, List optionList) {
    TypeOptionList tOptionList =  tom.engine.adt.tomtype.types.typeoptionlist.EmptyconcTypeOption.make() ;
    TomTypeList typeList =  tom.engine.adt.tomtype.types.tomtypelist.EmptyconcTomType.make() ;
    PairNameDeclList pairSlotDeclList =  tom.engine.adt.tomslot.types.pairnamedecllist.EmptyconcPairNameDecl.make() ;
    TomSymbol astSymbol = makeSymbol(value, tom.engine.adt.tomtype.types.tomtype.Type.make(tOptionList, sort,  tom.engine.adt.tomtype.types.targetlanguagetype.EmptyTargetLanguageType.make() ) ,typeList,pairSlotDeclList,optionList);
    symbolTable.putSymbol(value,astSymbol);
  }

    
  public static void makeIntegerSymbol(SymbolTable symbolTable,
                                String value, List optionList) {
    String sort = "int";
    makeSortSymbol(symbolTable, sort, value, optionList);
  }

    
  public static void makeLongSymbol(SymbolTable symbolTable,
                             String value, List optionList) {
    String sort = "long";
    makeSortSymbol(symbolTable, sort, value, optionList);
  }

    
  public static void makeCharSymbol(SymbolTable symbolTable,
                             String value, List optionList) {
    String sort = "char";
    makeSortSymbol(symbolTable, sort, value, optionList);
    
  }
    
  public static void makeDoubleSymbol(SymbolTable symbolTable,
                               String value, List optionList) {
    String sort = "double";
    makeSortSymbol(symbolTable, sort, value, optionList);
  }

    
  public static void makeStringSymbol(SymbolTable symbolTable,
                               String value, List optionList) {
    String sort = "String";
    makeSortSymbol(symbolTable, sort, value, optionList);
  }

    
  public static TomSymbol updateDefinedSymbol(SymbolTable symbolTable, TomTerm term) {
    if(term.isTermAppl() || term.isRecordAppl()) {
      String key = term.getNameList().getHeadconcTomName().getString();
      TomSymbol symbol = symbolTable.getSymbolFromName(key);
      if(symbol != null) {
        OptionList optionList = symbol.getOptions();
        optionList = tom_append_list_concOption(optionList, tom.engine.adt.tomoption.types.optionlist.ConsconcOption.make( tom.engine.adt.tomoption.types.option.DefinedSymbol.make() , tom.engine.adt.tomoption.types.optionlist.EmptyconcOption.make() ) );
        symbolTable.putSymbol(key,symbol.setOptions(optionList));
        return symbol;
      }
    }
    return null;
  }

  public static String makeSingleLineCode(String code, boolean pretty) {
    if(!pretty) {
      code = code.replace('\n', ' ');
      code = code.replace('\t', ' ');
      code = code.replace('\r', ' ');
    }
    return code;
  }

  public static TomName makeName(String slotName) {
    if(slotName.length()>0) {
      return  tom.engine.adt.tomname.types.tomname.Name.make(slotName) ;
    } else {
      return  tom.engine.adt.tomname.types.tomname.EmptyName.make() ;
    }
  }

  public final static String TOM_VARIABLE_SEPARATOR = "___";
  public static String makeTomVariableName(String prefix,String name) {
    return prefix + TOM_VARIABLE_SEPARATOR + name;
  }

  public static String extractRealNameFromTomVariableName(String fullName) {
    int index = fullName.indexOf(TOM_VARIABLE_SEPARATOR);
    if(index < 0) {
      return fullName;
    }

    return fullName.substring(index + TOM_VARIABLE_SEPARATOR.length());
  }

  public static BQTerm buildList(TomName name, BQTermList args, SymbolTable symbolTable) {
    TomSymbol topListSymbol = symbolTable.getSymbolFromName(name.getString());
    String topDomain = 
      TomBase.getTomType(TomBase.getSymbolDomain(topListSymbol).getHeadconcTomType());
    String topCodomain = TomBase.getTomType(TomBase.getSymbolCodomain(topListSymbol));
   
    { /* unamed block */{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if ( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() ) {

        return  tom.engine.adt.code.types.bqterm.BuildEmptyList.make(name) ;
      }}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) {if ( ((( tom.engine.adt.code.types.BQTerm ) (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {



        BQTerm subList = buildList(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,symbolTable);
        
        return  tom.engine.adt.code.types.bqterm.BuildAppendList.make(name,  (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() , subList) ;
      }}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch401_12= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if ( (((( tom.engine.adt.code.types.BQTerm )tomMatch401_12) instanceof tom.engine.adt.code.types.bqterm.ConsComposite) || ((( tom.engine.adt.code.types.BQTerm )tomMatch401_12) instanceof tom.engine.adt.code.types.bqterm.EmptyComposite)) ) {if (!( tomMatch401_12.isEmptyComposite() )) { tom.engine.adt.code.types.CompositeMember  tomMatch401_16= tomMatch401_12.getHeadComposite() ;if ( ((( tom.engine.adt.code.types.CompositeMember )tomMatch401_16) instanceof tom.engine.adt.code.types.compositemember.CompositeBQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm ) tomMatch401_16.getterm() ) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {


        BQTerm subList = buildList(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,symbolTable);
        return  tom.engine.adt.code.types.bqterm.BuildAppendList.make(name,  (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() , subList) ;
      }}}}}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch401_25= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_25) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) { tom.engine.adt.code.types.BQTerm  tom___head= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;











        
        
        

        BQTerm subList = buildList(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,symbolTable);
        
        if(topDomain != topCodomain) { /* unamed block */
          if(TomBase.getTomType( tomMatch401_25.getAstType() ) == topCodomain) { /* unamed block */
            return  tom.engine.adt.code.types.bqterm.BuildAppendList.make(name, tom___head, subList) ;
          }}

        return  tom.engine.adt.code.types.bqterm.BuildConsList.make(name, tom___head, subList) ;
      }}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch401_31= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if ( (((( tom.engine.adt.code.types.BQTerm )tomMatch401_31) instanceof tom.engine.adt.code.types.bqterm.ConsComposite) || ((( tom.engine.adt.code.types.BQTerm )tomMatch401_31) instanceof tom.engine.adt.code.types.bqterm.EmptyComposite)) ) { tom.engine.adt.code.types.BQTerm  tom___head= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if (!( tomMatch401_31.isEmptyComposite() )) { tom.engine.adt.code.types.CompositeMember  tomMatch401_35= tomMatch401_31.getHeadComposite() ;if ( ((( tom.engine.adt.code.types.CompositeMember )tomMatch401_35) instanceof tom.engine.adt.code.types.compositemember.CompositeBQTerm) ) { tom.engine.adt.code.types.BQTerm  tomMatch401_34= tomMatch401_35.getterm() ;if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_34) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {


        
        
        
        BQTerm subList = buildList(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,symbolTable);
        if(topDomain != topCodomain) { /* unamed block */
          if(TomBase.getTomType( tomMatch401_34.getAstType() ) == topCodomain) { /* unamed block */
            return  tom.engine.adt.code.types.bqterm.BuildAppendList.make(name, tom___head, subList) ;
          }}

        return  tom.engine.adt.code.types.bqterm.BuildConsList.make(name, tom___head, subList) ;
      }}}}}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch401_44= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if ( (((( tom.engine.adt.code.types.BQTerm )tomMatch401_44) instanceof tom.engine.adt.code.types.bqterm.ConsComposite) || ((( tom.engine.adt.code.types.BQTerm )tomMatch401_44) instanceof tom.engine.adt.code.types.bqterm.EmptyComposite)) ) { tom.engine.adt.code.types.BQTerm  tom___head= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if (!( tomMatch401_44.isEmptyComposite() )) { tom.engine.adt.code.types.CompositeMember  tomMatch401_48= tomMatch401_44.getHeadComposite() ;if ( ((( tom.engine.adt.code.types.CompositeMember )tomMatch401_48) instanceof tom.engine.adt.code.types.compositemember.CompositeBQTerm) ) { tom.engine.adt.code.types.BQTerm  tomMatch401_47= tomMatch401_48.getterm() ;if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_47) instanceof tom.engine.adt.code.types.bqterm.BuildConsList) ) {


        BQTerm subList = buildList(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,symbolTable);
        
        if(topDomain != topCodomain) { /* unamed block */
          if(name.equals( tomMatch401_47.getAstName() )) { /* unamed block */
            return  tom.engine.adt.code.types.bqterm.BuildAppendList.make(name, tom___head, subList) ;
          }}

        return  tom.engine.adt.code.types.bqterm.BuildConsList.make(name, tom___head, subList) ;
      }}}}}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch401_57= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if ( (((( tom.engine.adt.code.types.BQTerm )tomMatch401_57) instanceof tom.engine.adt.code.types.bqterm.ConsComposite) || ((( tom.engine.adt.code.types.BQTerm )tomMatch401_57) instanceof tom.engine.adt.code.types.bqterm.EmptyComposite)) ) { tom.engine.adt.code.types.BQTerm  tom___head= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if (!( tomMatch401_57.isEmptyComposite() )) { tom.engine.adt.code.types.CompositeMember  tomMatch401_61= tomMatch401_57.getHeadComposite() ;if ( ((( tom.engine.adt.code.types.CompositeMember )tomMatch401_61) instanceof tom.engine.adt.code.types.compositemember.CompositeBQTerm) ) { tom.engine.adt.code.types.BQTerm  tomMatch401_60= tomMatch401_61.getterm() ;if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_60) instanceof tom.engine.adt.code.types.bqterm.BuildTerm) ) { tom.engine.adt.tomname.types.TomName  tomMatch401_63= tomMatch401_60.getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch401_63) instanceof tom.engine.adt.tomname.types.tomname.Name) ) {


        BQTerm subList = buildList(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,symbolTable);
        if(topDomain != topCodomain) { /* unamed block */
        
          TomSymbol symbol = symbolTable.getSymbolFromName( tomMatch401_63.getString() );
          String codomain = TomBase.getTomType(TomBase.getSymbolCodomain(symbol));
          if(codomain == topCodomain) { /* unamed block */
            return  tom.engine.adt.code.types.bqterm.BuildAppendList.make(name, tom___head, subList) ;
          }}

        return  tom.engine.adt.code.types.bqterm.BuildConsList.make(name, tom___head, subList) ;
      }}}}}}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch401_73= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;boolean tomMatch401_81= false ; tom.engine.adt.code.types.BQTerm  tomMatch401_78= null ; tom.engine.adt.code.types.BQTerm  tomMatch401_77= null ; tom.engine.adt.code.types.BQTerm  tomMatch401_76= null ; tom.engine.adt.code.types.BQTerm  tomMatch401_79= null ; tom.engine.adt.code.types.BQTerm  tomMatch401_80= null ; tom.engine.adt.code.types.BQTerm  tomMatch401_74= null ; tom.engine.adt.code.types.BQTerm  tomMatch401_75= null ;if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_73) instanceof tom.engine.adt.code.types.bqterm.FunctionCall) ) {{ /* unamed block */tomMatch401_81= true ;tomMatch401_74=tomMatch401_73;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_73) instanceof tom.engine.adt.code.types.bqterm.BuildTerm) ) {{ /* unamed block */tomMatch401_81= true ;tomMatch401_75=tomMatch401_73;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_73) instanceof tom.engine.adt.code.types.bqterm.BuildConstant) ) {{ /* unamed block */tomMatch401_81= true ;tomMatch401_76=tomMatch401_73;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_73) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{ /* unamed block */tomMatch401_81= true ;tomMatch401_77=tomMatch401_73;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_73) instanceof tom.engine.adt.code.types.bqterm.BuildAppendList) ) {{ /* unamed block */tomMatch401_81= true ;tomMatch401_78=tomMatch401_73;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_73) instanceof tom.engine.adt.code.types.bqterm.BuildConsList) ) {{ /* unamed block */tomMatch401_81= true ;tomMatch401_79=tomMatch401_73;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch401_73) instanceof tom.engine.adt.code.types.bqterm.BuildEmptyList) ) {{ /* unamed block */tomMatch401_81= true ;tomMatch401_80=tomMatch401_73;}}}}}}}}if (tomMatch401_81) {


        BQTerm subList = buildList(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,symbolTable);
        return  tom.engine.adt.code.types.bqterm.BuildConsList.make(name,  (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() , subList) ;
      }}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch401_86= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if ( (((( tom.engine.adt.code.types.BQTerm )tomMatch401_86) instanceof tom.engine.adt.code.types.bqterm.ConsComposite) || ((( tom.engine.adt.code.types.BQTerm )tomMatch401_86) instanceof tom.engine.adt.code.types.bqterm.EmptyComposite)) ) { tom.engine.adt.code.types.BQTerm  tom___X=tomMatch401_86;


       BQTerm subList = buildList(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,symbolTable);
       
        { /* unamed block */{ /* unamed block */if ( (tom___X instanceof tom.engine.adt.code.types.BQTerm) ) {if ( (((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )tom___X)) instanceof tom.engine.adt.code.types.bqterm.ConsComposite) || ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )tom___X)) instanceof tom.engine.adt.code.types.bqterm.EmptyComposite)) ) {if (!( (( tom.engine.adt.code.types.BQTerm )tom___X).isEmptyComposite() )) { tom.engine.adt.code.types.CompositeMember  tomMatch402_4= (( tom.engine.adt.code.types.BQTerm )tom___X).getHeadComposite() ;if ( ((( tom.engine.adt.code.types.CompositeMember )tomMatch402_4) instanceof tom.engine.adt.code.types.compositemember.CompositeTL) ) { tom.engine.adt.code.types.TargetLanguage  tomMatch402_3= tomMatch402_4.getTl() ;if ( ((( tom.engine.adt.code.types.TargetLanguage )tomMatch402_3) instanceof tom.engine.adt.code.types.targetlanguage.ITL) ) {if (  (( tom.engine.adt.code.types.BQTerm )tom___X).getTailComposite() .isEmptyComposite() ) {

            if ( tomMatch402_3.getCode() .trim().equals("")) { /* unamed block */
              return subList;
            }}}}}}}}}


        return  tom.engine.adt.code.types.bqterm.BuildConsList.make(name,  (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() , subList) ;
      }}}}}}


    throw new TomRuntimeException("buildList strange term: " + args);
  }

  public static BQTerm buildArray(TomName name, BQTermList args, SymbolTable symbolTable) {
    return buildArray(name,args.reverse(),0, symbolTable);
  }

  private static BQTerm buildArray(TomName name, BQTermList args, int size, SymbolTable symbolTable) {
    TomSymbol topListSymbol = symbolTable.getSymbolFromName(name.getString());
    String topDomain = TomBase.getTomType(TomBase.getSymbolDomain(topListSymbol).getHeadconcTomType());
    String topCodomain = TomBase.getTomType(TomBase.getSymbolCodomain(topListSymbol));

    { /* unamed block */{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if ( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() ) {

        return  tom.engine.adt.code.types.bqterm.BuildEmptyArray.make(name,  tom.engine.adt.code.types.bqterm.ExpressionToBQTerm.make( tom.engine.adt.tomexpression.types.expression.Integer.make(size) ) ) ;
      }}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) {if ( ((( tom.engine.adt.code.types.BQTerm ) (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {


        BQTerm subList = buildArray(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,size+1,symbolTable);
        
        return  tom.engine.adt.code.types.bqterm.BuildAppendArray.make(name,  (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() , subList) ;
      }}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch403_12= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if ( (((( tom.engine.adt.code.types.BQTerm )tomMatch403_12) instanceof tom.engine.adt.code.types.bqterm.ConsComposite) || ((( tom.engine.adt.code.types.BQTerm )tomMatch403_12) instanceof tom.engine.adt.code.types.bqterm.EmptyComposite)) ) {if (!( tomMatch403_12.isEmptyComposite() )) { tom.engine.adt.code.types.CompositeMember  tomMatch403_16= tomMatch403_12.getHeadComposite() ;if ( ((( tom.engine.adt.code.types.CompositeMember )tomMatch403_16) instanceof tom.engine.adt.code.types.compositemember.CompositeBQTerm) ) {if ( ((( tom.engine.adt.code.types.BQTerm ) tomMatch403_16.getterm() ) instanceof tom.engine.adt.code.types.bqterm.BQVariableStar) ) {


        BQTerm subList = buildArray(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,size+1,symbolTable);
        return  tom.engine.adt.code.types.bqterm.BuildAppendArray.make(name,  (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() , subList) ;
      }}}}}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch403_25= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch403_25) instanceof tom.engine.adt.code.types.bqterm.BuildConsArray) ) { tom.engine.adt.code.types.BQTerm  tom___head= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;


        BQTerm subList = buildArray(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,size+1,symbolTable);
        
        if(topDomain != topCodomain) { /* unamed block */
          if(name.equals( tomMatch403_25.getAstName() )) { /* unamed block */
            return  tom.engine.adt.code.types.bqterm.BuildAppendArray.make(name, tom___head, subList) ;
          }}

        return  tom.engine.adt.code.types.bqterm.BuildConsArray.make(name, tom___head, subList) ;
      }}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch403_32= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch403_32) instanceof tom.engine.adt.code.types.bqterm.BuildTerm) ) { tom.engine.adt.tomname.types.TomName  tomMatch403_31= tomMatch403_32.getAstName() ;if ( ((( tom.engine.adt.tomname.types.TomName )tomMatch403_31) instanceof tom.engine.adt.tomname.types.tomname.Name) ) { tom.engine.adt.code.types.BQTerm  tom___head= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;


        BQTerm subList = buildArray(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,size+1,symbolTable);
        if(topDomain != topCodomain) { /* unamed block */
        
          TomSymbol symbol = symbolTable.getSymbolFromName( tomMatch403_31.getString() );
          String codomain = TomBase.getTomType(TomBase.getSymbolCodomain(symbol));
          if(codomain == topCodomain) { /* unamed block */
            return  tom.engine.adt.code.types.bqterm.BuildAppendArray.make(name, tom___head, subList) ;
          }}

        return  tom.engine.adt.code.types.bqterm.BuildConsArray.make(name, tom___head, subList) ;
      }}}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch403_41= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;boolean tomMatch403_45= false ; tom.engine.adt.code.types.BQTerm  tomMatch403_43= null ; tom.engine.adt.code.types.BQTerm  tomMatch403_42= null ; tom.engine.adt.code.types.BQTerm  tomMatch403_44= null ;if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch403_41) instanceof tom.engine.adt.code.types.bqterm.BuildTerm) ) {{ /* unamed block */tomMatch403_45= true ;tomMatch403_42=tomMatch403_41;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch403_41) instanceof tom.engine.adt.code.types.bqterm.BuildConstant) ) {{ /* unamed block */tomMatch403_45= true ;tomMatch403_43=tomMatch403_41;}} else {if ( ((( tom.engine.adt.code.types.BQTerm )tomMatch403_41) instanceof tom.engine.adt.code.types.bqterm.BQVariable) ) {{ /* unamed block */tomMatch403_45= true ;tomMatch403_44=tomMatch403_41;}}}}if (tomMatch403_45) {


        BQTerm subList = buildArray(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,size+1,symbolTable);
        return  tom.engine.adt.code.types.bqterm.BuildConsArray.make(name,  (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() , subList) ;
      }}}}}{ /* unamed block */if ( (args instanceof tom.engine.adt.code.types.BQTermList) ) {if ( (((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.ConsconcBQTerm) || ((( tom.engine.adt.code.types.BQTermList )args) instanceof tom.engine.adt.code.types.bqtermlist.EmptyconcBQTerm)) ) {if (!( (( tom.engine.adt.code.types.BQTermList )args).isEmptyconcBQTerm() )) { tom.engine.adt.code.types.BQTerm  tomMatch403_50= (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() ;if ( (((( tom.engine.adt.code.types.BQTerm )tomMatch403_50) instanceof tom.engine.adt.code.types.bqterm.ConsComposite) || ((( tom.engine.adt.code.types.BQTerm )tomMatch403_50) instanceof tom.engine.adt.code.types.bqterm.EmptyComposite)) ) { tom.engine.adt.code.types.BQTerm  tom___X=tomMatch403_50;


        BQTerm subList = buildArray(name, (( tom.engine.adt.code.types.BQTermList )args).getTailconcBQTerm() ,size+1,symbolTable);
        
        { /* unamed block */{ /* unamed block */if ( (tom___X instanceof tom.engine.adt.code.types.BQTerm) ) {if ( (((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )tom___X)) instanceof tom.engine.adt.code.types.bqterm.ConsComposite) || ((( tom.engine.adt.code.types.BQTerm )(( tom.engine.adt.code.types.BQTerm )tom___X)) instanceof tom.engine.adt.code.types.bqterm.EmptyComposite)) ) {if (!( (( tom.engine.adt.code.types.BQTerm )tom___X).isEmptyComposite() )) { tom.engine.adt.code.types.CompositeMember  tomMatch404_4= (( tom.engine.adt.code.types.BQTerm )tom___X).getHeadComposite() ;if ( ((( tom.engine.adt.code.types.CompositeMember )tomMatch404_4) instanceof tom.engine.adt.code.types.compositemember.CompositeTL) ) { tom.engine.adt.code.types.TargetLanguage  tomMatch404_3= tomMatch404_4.getTl() ;if ( ((( tom.engine.adt.code.types.TargetLanguage )tomMatch404_3) instanceof tom.engine.adt.code.types.targetlanguage.ITL) ) {if (  (( tom.engine.adt.code.types.BQTerm )tom___X).getTailComposite() .isEmptyComposite() ) {

            if ( tomMatch404_3.getCode() .trim().equals("")) { /* unamed block */
              return subList;
            }}}}}}}}}


        return  tom.engine.adt.code.types.bqterm.BuildConsArray.make(name,  (( tom.engine.adt.code.types.BQTermList )args).getHeadconcBQTerm() , subList) ;
      }}}}}}


    throw new TomRuntimeException("buildArray strange term: " + args);
  }

  
  public static String abstractCode(String code, String... vars) {
    int index=0;
    for(String var:vars) {
      code = code.replace("$"+var,"{"+index+"}");
      index++;
    }
    return code;
  }



}
