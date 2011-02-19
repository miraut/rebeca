from edu.cmu.cs.stage3.alice.authoringtool import JAlice
from edu.cmu.cs.stage3.util import StringTypePair
from java.lang import Boolean
from java.lang import Double
from java.lang import Integer
from java.lang import String
from edu.cmu.cs.stage3.math import Vector3
from edu.cmu.cs.stage3.math import Matrix44
import edu
import java
import javax
import string

# HACK: until os.path works
def os_path_join( *args ):
	return string.join( args, java.io.File.separator )

####################################
# load common resource data
####################################

standardResourcesFile = java.io.File( JAlice.getAliceHomeDirectory(), "resources/common/StandardResources.py" )
execfile( standardResourcesFile.getAbsolutePath() )


##################
# Format Config
##################

formatMap = {
	edu.cmu.cs.stage3.alice.core.question.visualization.model.Item : "el valor de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.response.visualization.model.SetItem : "dejar <<<subject>>> = <item>",

	edu.cmu.cs.stage3.alice.core.question.visualization.array.ItemAtIndex : "el valor de <<<subject>>>[ <index> ]",
	edu.cmu.cs.stage3.alice.core.response.visualization.array.SetItemAtIndex : "dejar <<<subject>>>[ <index> ] = <item>",
	edu.cmu.cs.stage3.alice.core.question.visualization.array.Size : "tamaño de <<<subject>>>",

	edu.cmu.cs.stage3.alice.core.question.visualization.list.Size : "tamaño de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.visualization.list.Contains : "<<<subject>>> contiene <item>",
	edu.cmu.cs.stage3.alice.core.question.visualization.list.IsEmpty : "<<<subject>>> esta vacío",
	edu.cmu.cs.stage3.alice.core.question.visualization.list.FirstIndexOfItem : "primer índice de <item> de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.visualization.list.LastIndexOfItem : "último índice de  <item> de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.visualization.list.ItemAtBeginning : "elemento al comienzo de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.visualization.list.ItemAtEnd : "elemento al final de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.visualization.list.ItemAtIndex : "elemento con índice <index> de <<<subject>>>",

	edu.cmu.cs.stage3.alice.core.response.visualization.list.InsertItemAtBeginning : "insertar <item> al comienzo de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.response.visualization.list.InsertItemAtEnd : "insertar <item> al final de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.response.visualization.list.InsertItemAtIndex : "insertar <item> en <index> de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.response.visualization.list.RemoveItemFromBeginning : "quitar el elemento del comienzo de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.response.visualization.list.RemoveItemFromEnd : "quitar el elemento del final de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.response.visualization.list.RemoveItemFromIndex : "quitar el elemento de <index> de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.response.visualization.list.Clear : "limpiar <<<subject>>>",

	edu.cmu.cs.stage3.alice.core.response.MoveAnimation : "<<<subject>>> mover <<direction>><<amount>>",
	edu.cmu.cs.stage3.alice.core.response.MoveTowardAnimation : "<<<subject>>> mover <<amount>> hacia <<target>>",
	edu.cmu.cs.stage3.alice.core.response.MoveAwayFromAnimation : "<<<subject>>> mover <<amount>> lejos de <<target>>",
	edu.cmu.cs.stage3.alice.core.response.TurnAnimation : "<<<subject>>> girar <<direction>><<amount>>",
	edu.cmu.cs.stage3.alice.core.response.RollAnimation : "<<<subject>>> rodar <<direction>><<amount>>",
	edu.cmu.cs.stage3.alice.core.response.MoveAtSpeed : "<<<subject>>> mover a velocidad <<direction>><<speed>>",
	edu.cmu.cs.stage3.alice.core.response.TurnAtSpeed : "<<<subject>>> mover a velocidad <<direction>><<speed>>",
	edu.cmu.cs.stage3.alice.core.response.RollAtSpeed : "<<<subject>>> rodar a velocidad <<direction>><<speed>>",
	edu.cmu.cs.stage3.alice.core.response.ResizeAnimation : "<<<subject>>> redimensionar <<amount>>",
	edu.cmu.cs.stage3.alice.core.response.PointAtAnimation : "<<<subject>>> apuntar a <<target>>",
	edu.cmu.cs.stage3.alice.core.response.TurnToFaceAnimation : "<<<subject>>> encarar a <<target>>",
	edu.cmu.cs.stage3.alice.core.response.TurnAwayFromAnimation : "<<<subject>>> girar en contra de <<target>>",
	edu.cmu.cs.stage3.alice.core.response.PointAtConstraint : "<<<subject>>> limitar al punto en <<target>>",
	edu.cmu.cs.stage3.alice.core.response.TurnToFaceConstraint : "<<<subject>>> limitar a encararse a <<target>>",
	edu.cmu.cs.stage3.alice.core.response.TurnAwayFromConstraint : "<<<subject>>> limitar a encararse en contra de <<target>>",
	edu.cmu.cs.stage3.alice.core.response.GetAGoodLookAtAnimation : "<<<subject>>> tomar una buena imagen de <<target>>",
	edu.cmu.cs.stage3.alice.core.response.StandUpAnimation : "<<<subject>>> arriba",
	edu.cmu.cs.stage3.alice.core.response.PositionAnimation : "<<<subject>>> mover a <<asSeenBy>>",
	edu.cmu.cs.stage3.alice.core.response.PlaceAnimation : "<<<subject>>> caitlin mover a <<amount>><<spatialRelation>><<asSeenBy>>",
	edu.cmu.cs.stage3.alice.core.response.QuaternionAnimation : "<<<subject>>> orientar a <<asSeenBy>>",
	edu.cmu.cs.stage3.alice.core.response.PointOfViewAnimation : "<<<subject>>> poner punto de vista <<asSeenBy>>",
	edu.cmu.cs.stage3.alice.core.response.PropertyAnimation : "<<<element>>> poner <propertyName> a <<value>>",
	edu.cmu.cs.stage3.alice.core.response.SoundResponse : "<<<subject>>> reproducir sonido <<sound>>",
	edu.cmu.cs.stage3.alice.core.response.Wait : "Esperar <<duration>>",
	edu.cmu.cs.stage3.alice.core.response.Comment : "// <<text>>",
	edu.cmu.cs.stage3.alice.core.response.Print : "imprimir <<text>> <<object>>",
	edu.cmu.cs.stage3.alice.core.response.CallToUserDefinedResponse : "<userDefinedResponse><requiredActualParameters>",
	edu.cmu.cs.stage3.alice.core.response.ScriptResponse : "Script <<script>>",
	edu.cmu.cs.stage3.alice.core.response.ScriptDefinedResponse : "Respuesta de Script-Definido <<script>>",
	edu.cmu.cs.stage3.alice.core.response.SayAnimation : "<<<subject>>> decir <<what>>",
	edu.cmu.cs.stage3.alice.core.response.ThinkAnimation : "<<<subject>>> pensar <<what>>",
	edu.cmu.cs.stage3.pratt.maxkeyframing.PositionKeyframeResponse : "posición de animación de fotograma clave <<subject>>",
	edu.cmu.cs.stage3.pratt.maxkeyframing.QuaternionKeyframeResponse : "orientación de animación de fotograma clave <<subject>>",
	edu.cmu.cs.stage3.pratt.maxkeyframing.ScaleKeyframeResponse : "escala de animación de fotograma clave <<subject>>",
	edu.cmu.cs.stage3.pratt.maxkeyframing.KeyframeResponse : "animación de fotograma clave <<subject>>",
	edu.cmu.cs.stage3.alice.core.response.PoseAnimation : "<<<subject>>> poner postura <<pose>>",
	edu.cmu.cs.stage3.alice.core.response.Increment : "incrementar <<<variable>>> en 1",
	edu.cmu.cs.stage3.alice.core.response.Decrement : "decrementar <<<variable>>> en 1",

	edu.cmu.cs.stage3.alice.core.response.VehiclePropertyAnimation : "<element> poner <propertyName> a <value>",

	edu.cmu.cs.stage3.alice.core.response.list.InsertItemAtBeginning : "insertar <item> al comienzo de <<<list>>>",
	edu.cmu.cs.stage3.alice.core.response.list.InsertItemAtEnd : "insertar <item> al final de <<<list>>>",
	edu.cmu.cs.stage3.alice.core.response.list.InsertItemAtIndex : "insertar <item> en posición <index> de <<<list>>>",
	edu.cmu.cs.stage3.alice.core.response.list.RemoveItemFromBeginning : "borrar elemento desde el comienzo de <<<list>>>",
	edu.cmu.cs.stage3.alice.core.response.list.RemoveItemFromEnd : "quitar elemento desde el final de <<<list>>>",
	edu.cmu.cs.stage3.alice.core.response.list.RemoveItemFromIndex : "quitar elemento desde posición <index> de <<<list>>>",
	edu.cmu.cs.stage3.alice.core.response.list.Clear : "quitar todos los elementos de <<<list>>>",

	edu.cmu.cs.stage3.alice.core.response.array.SetItemAtIndex : "poner elemento <index> a <item> en <<<array>>>",

	edu.cmu.cs.stage3.alice.core.response.vector3.SetX : "poner la distancia derecha de <<<vector3>>> a <<value>>",
	edu.cmu.cs.stage3.alice.core.response.vector3.SetY : "poner la distancia arriba de <<<vector3>>> a <<value>>",
	edu.cmu.cs.stage3.alice.core.response.vector3.SetZ : "poner la distancia delante de <<<vector3>>> a <<value>>",

	edu.cmu.cs.stage3.alice.core.question.userdefined.CallToUserDefinedQuestion : "<userDefinedQuestion><requiredActualParameters>",
	edu.cmu.cs.stage3.alice.core.question.userdefined.Return : "Devolver <<value>>",
	edu.cmu.cs.stage3.alice.core.question.userdefined.Comment : "// <<text>>",
	edu.cmu.cs.stage3.alice.core.question.userdefined.Print : "imprimir <<text>> <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.userdefined.PropertyAssignment : "<element> poner <propertyName> a <value>",

	edu.cmu.cs.stage3.alice.core.question.PartKeyed : "parte de <<<owner>>> llamada <key>",
	edu.cmu.cs.stage3.alice.core.question.VariableNamed : "variable de <<<owner>>> llamada <variableName> de tipo <valueClass>",

	edu.cmu.cs.stage3.alice.core.question.Width : "ancho de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.Height : "alto de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.Depth : "profundidad de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.Quaternion : "cuaternio de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.Position : "posición de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.PointOfView : "punto de vista de <<<subject>>>",

	edu.cmu.cs.stage3.alice.core.question.Not : "no <a>",
	edu.cmu.cs.stage3.alice.core.question.And : "ambos <a> y <b>",
	edu.cmu.cs.stage3.alice.core.question.Or : "cualquiera <a> o <b>, o ambos",

	edu.cmu.cs.stage3.alice.core.question.StringConcatQuestion : "<a> unido con <b>",
	edu.cmu.cs.stage3.alice.core.question.ToStringQuestion : "<que> como una cadena",

	edu.cmu.cs.stage3.alice.core.question.ask.AskUserForNumber : "preguntar usuario por número <<question>>",
	edu.cmu.cs.stage3.alice.core.question.ask.AskUserYesNo : "preguntar usuario sí o no <<question>>",
	edu.cmu.cs.stage3.alice.core.question.ask.AskUserForString : "preguntar usuario por cadena <<question>>",

	edu.cmu.cs.stage3.alice.core.question.IsEqualTo : "<a>==<b>",
	edu.cmu.cs.stage3.alice.core.question.IsNotEqualTo : "<a>!=<b>",

	edu.cmu.cs.stage3.alice.core.question.NumberIsEqualTo : "<a>==<b>",
	edu.cmu.cs.stage3.alice.core.question.NumberIsNotEqualTo : "<a>!=<b>",
	edu.cmu.cs.stage3.alice.core.question.NumberIsGreaterThan : "<a>><b>",
	edu.cmu.cs.stage3.alice.core.question.NumberIsGreaterThanOrEqualTo : "<a>>=<b>",
	edu.cmu.cs.stage3.alice.core.question.NumberIsLessThan : "<a>&lt;<b>",
	edu.cmu.cs.stage3.alice.core.question.NumberIsLessThanOrEqualTo : "<a>&lt;=<b>",

	edu.cmu.cs.stage3.alice.core.question.NumberAddition : "(<a>+<b>)",
	edu.cmu.cs.stage3.alice.core.question.NumberSubtraction : "(<a>-<b>)",
	edu.cmu.cs.stage3.alice.core.question.NumberMultiplication : "(<a>*<b>)",
	edu.cmu.cs.stage3.alice.core.question.NumberDivision : "(<a>/<b>)",

	edu.cmu.cs.stage3.alice.core.question.math.Min : "mínimo de <a> y <b>",
	edu.cmu.cs.stage3.alice.core.question.math.Max : "máximo de <a> y <b>",
	edu.cmu.cs.stage3.alice.core.question.math.Abs : "valor absoluto de <a>",
	edu.cmu.cs.stage3.alice.core.question.math.Sqrt : "raíz cuadrada de <a>",
	edu.cmu.cs.stage3.alice.core.question.math.Floor : "floor <a>",
	edu.cmu.cs.stage3.alice.core.question.math.Ceil : "ceiling <a>",
	edu.cmu.cs.stage3.alice.core.question.math.Sin : "sin <a>",
	edu.cmu.cs.stage3.alice.core.question.math.Cos : "cos <a>",
	edu.cmu.cs.stage3.alice.core.question.math.Tan : "tan <a>",
	edu.cmu.cs.stage3.alice.core.question.math.ASin : "arcsin <a>",
	edu.cmu.cs.stage3.alice.core.question.math.ACos : "arccos <a>",
	edu.cmu.cs.stage3.alice.core.question.math.ATan : "arctan <a>",
	edu.cmu.cs.stage3.alice.core.question.math.ATan2 : "arctan2 <a><b>",
	edu.cmu.cs.stage3.alice.core.question.math.Pow : "<a> elevado a la potencia <b>",
	edu.cmu.cs.stage3.alice.core.question.math.Log : "logaritmo nepeariano <a>",
	edu.cmu.cs.stage3.alice.core.question.math.Exp : "e elevado a la potencia <a>",
	edu.cmu.cs.stage3.alice.core.question.math.IEEERemainder : "RestoIEEE de <a>/<b>",
	edu.cmu.cs.stage3.alice.core.question.math.Round : "redondear <a>",
	edu.cmu.cs.stage3.alice.core.question.math.ToDegrees : "<a> convertido radianes-grados",
	edu.cmu.cs.stage3.alice.core.question.math.ToRadians : "<a> convertido grados-radianes",
	edu.cmu.cs.stage3.alice.core.question.math.SuperSqrt : "la <b>-ésima raíz de <a>",

	edu.cmu.cs.stage3.alice.core.question.mouse.DistanceFromLeftEdge : "distancia ratón a margen izquierdo",
	edu.cmu.cs.stage3.alice.core.question.mouse.DistanceFromTopEdge : "distancia ratón a margen superior",

	edu.cmu.cs.stage3.alice.core.question.time.TimeElapsedSinceWorldStart : "tiempo transcurrido",

	edu.cmu.cs.stage3.alice.core.question.time.Year : "año",
	edu.cmu.cs.stage3.alice.core.question.time.MonthOfYear : "mes del año",
	edu.cmu.cs.stage3.alice.core.question.time.DayOfYear : "día del año",
	edu.cmu.cs.stage3.alice.core.question.time.DayOfMonth : "día del mes",
	edu.cmu.cs.stage3.alice.core.question.time.DayOfWeek : "día de la semana",
	edu.cmu.cs.stage3.alice.core.question.time.DayOfWeekInMonth : "día de la semana en el mes",
	edu.cmu.cs.stage3.alice.core.question.time.IsAM : "es AM",
	edu.cmu.cs.stage3.alice.core.question.time.IsPM : "es PM",
	edu.cmu.cs.stage3.alice.core.question.time.HourOfAMOrPM : "hora de AM o PM",
	edu.cmu.cs.stage3.alice.core.question.time.HourOfDay : "hora del día",
	edu.cmu.cs.stage3.alice.core.question.time.MinuteOfHour : "minuto de la hora",
	edu.cmu.cs.stage3.alice.core.question.time.SecondOfMinute : "segundo del minuto",

	edu.cmu.cs.stage3.alice.core.question.RandomBoolean : "verdadero <probabilidadVerdad> desde hora",
	edu.cmu.cs.stage3.alice.core.question.RandomNumber : "número aleatorio",

	edu.cmu.cs.stage3.alice.core.question.list.Contains : "<list> contiene <item>",
	edu.cmu.cs.stage3.alice.core.question.list.FirstIndexOfItem : "primer índice del <item> desde <list>",
	edu.cmu.cs.stage3.alice.core.question.list.IsEmpty : "<list> está vacía",
	edu.cmu.cs.stage3.alice.core.question.list.ItemAtBeginning : "primer elemento desde <list>",
	edu.cmu.cs.stage3.alice.core.question.list.ItemAtEnd : "último elemento desde <list>",
	edu.cmu.cs.stage3.alice.core.question.list.ItemAtIndex : "elemento <index> desde <list>",
	edu.cmu.cs.stage3.alice.core.question.list.ItemAtRandomIndex : "elemento aleatorio desde <list>",
	edu.cmu.cs.stage3.alice.core.question.list.LastIndexOfItem : "último índice de <item> desde <list>",
	edu.cmu.cs.stage3.alice.core.question.list.Size : "tamaño de <list>",

	edu.cmu.cs.stage3.alice.core.question.array.ItemAtIndex : "elemento <index> desde <<<array>>>",
	edu.cmu.cs.stage3.alice.core.question.array.Size : "tamaño de <<<array>>>",

	edu.cmu.cs.stage3.alice.core.question.IsAbove : "<<<subject>>> está sobre <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsBehind : "<<<subject>>> está detrás <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsBelow : "<<<subject>>> está debajo <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsInFrontOf : "<<<subject>>> está en frente de <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsLeftOf : "<<<subject>>> está a la izquierda de <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsRightOf : "<<<subject>>> está a la derecha de <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsSmallerThan : "<<<subject>>> es más pequeño que <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsLargerThan : "<<<subject>>> es más grande que <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsNarrowerThan : "<<<subject>>> es más estrecho que <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsWiderThan : "<<<subject>>> es más corto que <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsShorterThan : "<<<subject>>> es más corto que <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.IsTallerThan : "<<<subject>>> es más alto que <<objeto>>",

	edu.cmu.cs.stage3.alice.core.question.IsCloseTo : "<<<subject>>> está dentro de <umbral> de <objeto>",
	edu.cmu.cs.stage3.alice.core.question.IsFarFrom : "<<<subject>>> está al menos <umbral> lejos de <objeto>",
	edu.cmu.cs.stage3.alice.core.question.DistanceTo : "<<<subject>>> distancia a <<objeto>>",

	edu.cmu.cs.stage3.alice.core.question.DistanceToTheLeftOf : "<<<subject>>> distancia a la derecha de <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.DistanceToTheRightOf : "<<<subject>>> distancia a la izquierda de <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.DistanceAbove : "<<<subject>>> distancia sobre <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.DistanceBelow : "<<<subject>>> distancia debajo <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.DistanceInFrontOf : "<<<subject>>> distancia en frente de <<objeto>>",
	edu.cmu.cs.stage3.alice.core.question.DistanceBehind : "<<<subject>>> distancia detrás <<objeto>>",

	edu.cmu.cs.stage3.alice.core.question.vector3.X : "distancia a la derecha de <<<vector3>>>",
	edu.cmu.cs.stage3.alice.core.question.vector3.Y : "distancia encima de <<<vector3>>>",
	edu.cmu.cs.stage3.alice.core.question.vector3.Z : "distancia delante de <<<vector3>>>",

	edu.cmu.cs.stage3.alice.core.question.PickQuestion : "objeto bajo el cursor del ratón",

	edu.cmu.cs.stage3.alice.core.question.RightUpForward : "<derecha>, <arriba>, <adelante>",

	edu.cmu.cs.stage3.alice.core.question.Pose : "postura actual de <<<subject>>>",
	edu.cmu.cs.stage3.alice.core.question.PropertyValue : "<<<element>>>.<propertyName>",
}



##################
# Name Config
##################

nameMap = {

        "edu.cmu.cs.stage3.alice.core.ReferenceFrame.isBoundingBoxShowing" : "mostrarCuadroDelimitador",
        "edu.cmu.cs.stage3.alice.core.ReferenceFrame.isBoundingSphereShowing" : "mostrarEsferaDelimitadora",
        "edu.cmu.cs.stage3.alice.core.ReferenceFrame.eventsStopAscending" : "ascenderEventosParada",

        "edu.cmu.cs.stage3.alice.core.Element.isFirstClass" : "esPrimeraClase",

        "edu.cmu.cs.stage3.alice.core.Light.brightness" : "brillo",
        "edu.cmu.cs.stage3.alice.core.Light.range" : "rango",

        "edu.cmu.cs.stage3.alice.core.Transformable.isPivotShowing" : "mostrarPivote",
        "edu.cmu.cs.stage3.alice.core.Transformable.vehicle" : "vehículo",
        "vehicle" : "vehículo",
        "edu.cmu.cs.stage3.alice.core.Camera.nearClippingPlaneDistance" : "distanciaPlanoClippingCercano",
        "edu.cmu.cs.stage3.alice.core.Camera.farClippingPlaneDistance" : "distanciaPlanoClippingLejano",
        "edu.cmu.cs.stage3.alice.core.Camera.renderTarget" : "objetivoDeRender",

        "edu.cmu.cs.stage3.alice.core.camera.SymmetricPerspectiveCamera.verticalViewingAngle" : "vistaAnguloVertical",
        "verticalViewingAngle" : "vistaAnguloVertical",
        "edu.cmu.cs.stage3.alice.core.camera.SymmetricPerspectiveCamera.horizontalViewingAngle" : "vistaAnguloHorizontal",
        "horizontalViewingAngle" : "vistaAnguloHorizontal",

	"edu.cmu.cs.stage3.alice.core.response.DoInOrder" : "Hacer en orden",
	"edu.cmu.cs.stage3.alice.core.response.DoTogether" : "Hacer a la vez",
	"edu.cmu.cs.stage3.alice.core.response.IfElseInOrder" : "Si/Sino",
	"edu.cmu.cs.stage3.alice.core.response.LoopNInOrder" : "Bucle",
	"edu.cmu.cs.stage3.alice.core.response.WhileLoopInOrder" : "Mientras",
	"edu.cmu.cs.stage3.alice.core.response.ForEachInOrder" : "Para todos en orden",
	"edu.cmu.cs.stage3.alice.core.response.ForEachTogether" : "Para todos juntos",
	"edu.cmu.cs.stage3.alice.core.response.Print" : "Imprimir",
	"edu.cmu.cs.stage3.alice.core.response.QuaternionAnimation.quaternion" : "desplazamiento de",
	"edu.cmu.cs.stage3.alice.core.response.PointOfViewAnimation.pointOfView" : "punto de vista de",
	"edu.cmu.cs.stage3.alice.core.response.PositionAnimation.position" : "desplazamiento de",
        "edu.cmu.cs.stage3.alice.core.Response.duration" : "duración",
        "duration" : "duración",
        "edu.cmu.cs.stage3.alice.core.response.MoveAtSpeed.isScaledBySize" : "escaladoPorTamaño",
        "edu.cmu.cs.stage3.alice.core.response.TransformAnimation.asSeenBy" : "vistoPor",
        "edu.cmu.cs.stage3.alice.core.response.TransformResponse.asSeenBy" : "vistoPor",
        "edu.cmu.cs.stage3.alice.core.response.TransformAnimation.asSeenBy" : "vistoPor",
        "edu.cmu.cs.stage3.alice.core.behavior.BillboardBehavior.asSeenBy" : "vistoPor",
        "edu.cmu.cs.stage3.alice.core.question.SubjectAsSeenByQuestion.asSeenBy" : "vistoPor",
        "edu.cmu.cs.stage3.alice.core.question.IsInSpatialRelationTo.asSeenBy" : "vistoPor",
        "edu.cmu.cs.stage3.alice.core.question.SpatialRelationDistanceQuestion.asSeenBy" : "vistoPor",
        "asSeenBy" : "vistoPor",

        "edu.cmu.cs.stage3.alice.core.response.Animation.style" : "estilo",
        "edu.cmu.cs.stage3.alice.core.response.MoveAnimation.isScaledBySize" : "escaladoPorTamaño",

	"edu.cmu.cs.stage3.alice.core.question.userdefined.Return" : "Devolver",

	"edu.cmu.cs.stage3.alice.core.behavior.WorldStartBehavior" : "Cuando el mundo comienza",
	"edu.cmu.cs.stage3.alice.core.behavior.WorldIsRunningBehavior" : "Mientras el mundo está corriendo",
	"edu.cmu.cs.stage3.alice.core.behavior.KeyClickBehavior" : "Cuando <keyCode> es pulsada",
	"edu.cmu.cs.stage3.alice.core.behavior.KeyIsPressedBehavior" : "Mientras <keyCode> es presionada",
	"edu.cmu.cs.stage3.alice.core.behavior.MouseButtonClickBehavior" : "Cuando <mouse> haga clic en <onWhat>",
	"edu.cmu.cs.stage3.alice.core.behavior.MouseButtonIsPressedBehavior" : "Mientras <mouse> presione en <onWhat>",
	"edu.cmu.cs.stage3.alice.core.behavior.ConditionalBehavior" : "Mientras <condition> sea verdadero",
	"edu.cmu.cs.stage3.alice.core.behavior.ConditionalTriggerBehavior" : "Cuando <condition> se convierta en verdadero",
	"edu.cmu.cs.stage3.alice.core.behavior.VariableChangeBehavior" : "Cuando <variable> cambie",
	"edu.cmu.cs.stage3.alice.core.behavior.MessageReceivedBehavior" : "Cuando un mensaje sea recibido por <toWhom> desde <fromWho>",
	"edu.cmu.cs.stage3.alice.core.behavior.DefaultMouseInteractionBehavior" : "Dejar a <mouse> mover <objects>",
	"edu.cmu.cs.stage3.alice.core.behavior.KeyboardNavigationBehavior" : "Dejar a <arrowKeys> mover <subject>",
	"edu.cmu.cs.stage3.alice.core.behavior.MouseNavigationBehavior" : "Dejar a <mouse> mover la cámara",
	"edu.cmu.cs.stage3.alice.core.behavior.MouseLookingBehavior" : "Dejar a <mouse> orientar la cámara",
	"edu.cmu.cs.stage3.alice.core.behavior.SoundMarkerPassedBehavior" : "Cuando la señal de sonido <marker> esté reproduciendose",
	"edu.cmu.cs.stage3.alice.core.behavior.SoundLevelBehavior" : "Cuando el nivel de grabación de sonido sea >= <level>",

	"edu.cmu.cs.stage3.alice.core.Model.opacity" : "opacidad",
        "opacity" : "opacidad",
	"edu.cmu.cs.stage3.alice.core.Model.diffuseColorMap" : "texturaDePiel",
	"diffuseColorMap" : "texturaDePiel",
        "edu.cmu.cs.stage3.alice.core.Model.fillingStyle" : "estiloDeRelleno",
        "fillingStyle" : "estiloDeRelleno",
        "edu.cmu.cs.stage3.alice.core.Model.isShowing" : "mostrar",
        "edu.cmu.cs.stage3.alice.core.Model.emissiveColor" : "colorEmitido",
        "edu.cmu.cs.stage3.alice.core.Model.specularHighlightColor" : "colorLuminosidadEspecular",
        "edu.cmu.cs.stage3.alice.core.Model.specularHighlightExponent" : "exponenteLuminosidadEspecular",
        "edu.cmu.cs.stage3.alice.core.Model.shadingStyle" : "estiloSombreado",
        "edu.cmu.cs.stage3.alice.core.Model.opacityMap" : "mapaOpacidad",

        "edu.cmu.cs.stage3.alice.core.Transformable.localTransformation" : "puntoDeVista",
	"edu.cmu.cs.stage3.alice.core.behavior.MouseButtonClickBehavior.onWhat" : "alguna cosa",
	"edu.cmu.cs.stage3.alice.core.behavior.MouseButtonIsPressedBehavior.onWhat" : "alguna cosa",
	"edu.cmu.cs.stage3.alice.core.question.IsCloseTo.threshold" : "está dentro de",
	"edu.cmu.cs.stage3.alice.core.question.IsFarFrom.threshold" : "es por lo menos",
	"edu.cmu.cs.stage3.alice.core.question.IsCloseTo.object" : "de",
	"edu.cmu.cs.stage3.alice.core.question.IsFarFrom.object" : "lejos de",

	"edu.cmu.cs.stage3.alice.scenegraph.renderer.directx7renderer.Renderer" : "DirectX 7",
	"edu.cmu.cs.stage3.alice.scenegraph.renderer.openglrenderer.Renderer" : "OpenGL",
	"edu.cmu.cs.stage3.alice.scenegraph.renderer.java3drenderer.Renderer" : "Java3D",
	"edu.cmu.cs.stage3.alice.scenegraph.renderer.joglrenderer.Renderer" : "JOGL",
	"edu.cmu.cs.stage3.alice.scenegraph.renderer.nullrenderer.Renderer" : "Ninguno",

        "edu.cmu.cs.stage3.alice.core.question.RightUpForward.right" : "derecha",
        "right" : "derecha",

        "edu.cmu.cs.stage3.alice.core.World.atmosphereColor" :"colorAtmosfera",
        "atmoshereColor" : "colorAtmosfera",
        "edu.cmu.cs.stage3.alice.core.World.ambientLightColor" : "colorLuzAmbiente",
        "ambientLightColor" : "colorLuzAmbiente",
        "edu.cmu.cs.stage3.alice.core.World.ambientLightBrightness": "brilloLuzAmbiente",
        "ambientLightBrightness": "brilloLuzAmbiente",
        "edu.cmu.cs.stage3.alice.core.World.fogStyle" : "estiloNiebla",
        "fogStyle" : "estiloNiebla",
        "edu.cmu.cs.stage3.alice.core.World.fogDensity" : "densidadNiebla",
        "fogDensity" : "densidadNiebla",
        "edu.cmu.cs.stage3.alice.core.World.fogNearDistance" : "distanciaNieblaCerca",
        "fogNearDistance" : "distanciaNieblaCerca",
        "edu.cmu.cs.stage3.alice.core.World.fogFarDistance" : "distanciaNieblaLejos",
        "fogFarDistance" : "distanciaNieblaLejos",
        "edu.cmu.cs.stage3.alice.core.World.speedMultiplier" : "multiplicadorVelocidad",
        "speedMultiplier" : "multiplicadorVelocidad",
        "edu.cmu.cs.stage3.alice.core.camera.verticalViewingAngle" : "anguloVistaVertical",
        "verticalViewingAngle" : "anguloVistaVertical",

	edu.cmu.cs.stage3.alice.core.style.TraditionalAnimationStyle.BEGIN_AND_END_GENTLY : "suavemente",
	edu.cmu.cs.stage3.alice.core.style.TraditionalAnimationStyle.BEGIN_GENTLY_AND_END_ABRUPTLY : "comenzar suavemente",
	edu.cmu.cs.stage3.alice.core.style.TraditionalAnimationStyle.BEGIN_ABRUPTLY_AND_END_GENTLY : "acabar suavemente",
	edu.cmu.cs.stage3.alice.core.style.TraditionalAnimationStyle.BEGIN_AND_END_ABRUPTLY : "abruptamente",

	edu.cmu.cs.stage3.alice.core.Direction.LEFT : "izquierda",
	edu.cmu.cs.stage3.alice.core.Direction.RIGHT : "derecha",
	edu.cmu.cs.stage3.alice.core.Direction.UP : "arriba",
	edu.cmu.cs.stage3.alice.core.Direction.DOWN : "abajo",
	edu.cmu.cs.stage3.alice.core.Direction.FORWARD : "adelante",
	edu.cmu.cs.stage3.alice.core.Direction.BACKWARD : "atrás",

	edu.cmu.cs.stage3.alice.core.SpatialRelation.LEFT_OF : "izquierda de",
	edu.cmu.cs.stage3.alice.core.SpatialRelation.RIGHT_OF : "derecha de",
	edu.cmu.cs.stage3.alice.core.SpatialRelation.ABOVE : "encima",
	edu.cmu.cs.stage3.alice.core.SpatialRelation.BELOW : "debajo",
	edu.cmu.cs.stage3.alice.core.SpatialRelation.IN_FRONT_OF : "en frente de",
	edu.cmu.cs.stage3.alice.core.SpatialRelation.BEHIND : "detrás",

	edu.cmu.cs.stage3.alice.core.Dimension.ALL : "todo",
	edu.cmu.cs.stage3.alice.core.Dimension.LEFT_TO_RIGHT : "derecha a izquierda",
	edu.cmu.cs.stage3.alice.core.Dimension.TOP_TO_BOTTOM : "arriba a abajo",
	edu.cmu.cs.stage3.alice.core.Dimension.FRONT_TO_BACK : "adelante hacia atrás",

	edu.cmu.cs.stage3.alice.core.FogStyle.NONE : "sin niebla",
	edu.cmu.cs.stage3.alice.core.FogStyle.LINEAR : "distancia",
	edu.cmu.cs.stage3.alice.core.FogStyle.EXPONENTIAL : "densidad",

	edu.cmu.cs.stage3.alice.scenegraph.FillingStyle.SOLID : "sólido",
 	edu.cmu.cs.stage3.alice.scenegraph.FillingStyle.WIREFRAME : "malla",
	edu.cmu.cs.stage3.alice.scenegraph.FillingStyle.POINTS : "puntos",

	edu.cmu.cs.stage3.alice.scenegraph.ShadingStyle.NONE : "ninguno",
	edu.cmu.cs.stage3.alice.scenegraph.ShadingStyle.FLAT : "simple",
	edu.cmu.cs.stage3.alice.scenegraph.ShadingStyle.SMOOTH : "suave",

	java.lang.Boolean : "Boolean",
	java.lang.Number : "Number",
	edu.cmu.cs.stage3.alice.core.Model : "Objeto",

	Boolean.TRUE : "verdadero",
	Boolean.FALSE : "falso",

	edu.cmu.cs.stage3.alice.scenegraph.Color.WHITE : "sin color",
	edu.cmu.cs.stage3.alice.scenegraph.Color.BLACK : "negro",
	edu.cmu.cs.stage3.alice.scenegraph.Color.RED : "rojo",
	edu.cmu.cs.stage3.alice.scenegraph.Color.GREEN : "verde",
	edu.cmu.cs.stage3.alice.scenegraph.Color.BLUE : "azul",
	edu.cmu.cs.stage3.alice.scenegraph.Color.YELLOW : "amarillo",
	edu.cmu.cs.stage3.alice.scenegraph.Color.PURPLE : "púrpura",
	edu.cmu.cs.stage3.alice.scenegraph.Color.ORANGE : "naranja",
	edu.cmu.cs.stage3.alice.scenegraph.Color.PINK : "rosa",
	edu.cmu.cs.stage3.alice.scenegraph.Color.BROWN : "marrón",
	edu.cmu.cs.stage3.alice.scenegraph.Color.CYAN : "cian",
	edu.cmu.cs.stage3.alice.scenegraph.Color.MAGENTA : "magenta",
	edu.cmu.cs.stage3.alice.scenegraph.Color.GRAY : "gris",
	edu.cmu.cs.stage3.alice.scenegraph.Color.LIGHT_GRAY : "gris claro",
	edu.cmu.cs.stage3.alice.scenegraph.Color.DARK_GRAY : "gris oscuro",

	edu.cmu.cs.stage3.util.HowMuch.INSTANCE : "objeto solo",
	edu.cmu.cs.stage3.util.HowMuch.INSTANCE_AND_PARTS : "objeto y partes",
	edu.cmu.cs.stage3.util.HowMuch.INSTANCE_AND_ALL_DESCENDANTS : "objeto y todos los descendientes",    

}

htmlNameMap = {
	"edu.cmu.cs.stage3.alice.core.Transformable" : "[Obj]",
	"edu.cmu.cs.stage3.alice.core.Model" : "[Obj]",
	"java.lang.Number" : "[123]",
	"java.lang.Boolean" : "[V/F]",
	"java.lang.String" : "[ABC]",
	"edu.cmu.cs.stage3.alice.scenegraph.Color" : "[Color]",
	"edu.cmu.cs.stage3.alice.core.TextureMap" : "[Textura]",
	"edu.cmu.cs.stage3.alice.core.Sound" : "[Sonido]",
	"edu.cmu.cs.stage3.alice.core.Pose" : "[Pose]",
	"edu.cmu.cs.stage3.math.Vector3" : "[Pos]",
	"edu.cmu.cs.stage3.math.Quaternion" : "[Ori]",
	"edu.cmu.cs.stage3.math.Matrix44" : "[POV]",
	"edu.cmu.cs.stage3.alice.core.ReferenceFrame" : "[Obj]",
	"edu.cmu.cs.stage3.alice.core.Light" : "[Luz]",
	"edu.cmu.cs.stage3.alice.core.Direction" : "[Dirección]",
	"edu.cmu.cs.stage3.alice.core.Collection" : "]]]",
}


####################
# Color Config
####################

colorMap = {
	"disabledHTMLText": java.awt.Color( 200, 200, 200 ),
	"disabledHTML": java.awt.Color( 230, 230, 230 ),
	"DoInOrder" : java.awt.Color( 255, 255, 210 ),
	"DoTogether" : java.awt.Color( 238, 221, 255 ),
	"IfElseInOrder" : java.awt.Color( 204, 238, 221 ),
	"LoopNInOrder" : java.awt.Color( 221, 249, 249 ),
	"WhileLoopInOrder" : java.awt.Color( 204, 255, 221 ),
	"ForEach" : java.awt.Color( 255, 230, 230 ),
	"ForEachInOrder" : java.awt.Color( 255, 230, 230 ),
	"ForAllTogether" : java.awt.Color( 248, 221, 255 ),
	"Wait" : java.awt.Color( 255, 230, 180 ),
	"ScriptResponse" : java.awt.Color( 255, 230, 180 ),
	"ScriptDefinedResponse" : java.awt.Color( 255, 230, 180 ),
	"Print" : java.awt.Color( 255, 230, 180 ),
	"Comment" : java.awt.Color( 255, 255, 255 ),
	"Return" : java.awt.Color( 212, 204, 249 ),
	"PropertyAssignment" : java.awt.Color( 255, 230, 180 ),
	"accessibleMathTile" : java.awt.Color( 255, 230, 180 ),
	"dndHighlight" : java.awt.Color( 255, 255, 0 ),
	"dndHighlight2" : java.awt.Color( 0, 200, 0 ),
	"dndHighlight3" : java.awt.Color( 230, 0, 0 ),
	"propertyViewControllerBackground" : java.awt.Color( 240, 240, 255 ),
	"objectTreeSelected" : java.awt.Color( 96, 32, 200 ),
	"objectTreeBackground" : java.awt.Color( 240, 233, 207 ),
	"objectTreeDisabled" : java.awt.Color( 220, 220, 220 ),
	"objectTreeText" : java.awt.Color( 0, 0, 0 ),
	"objectTreeDisabledText" : java.awt.Color( 150, 150, 150 ),
	"objectTreeSelectedText" : java.awt.Color( 240, 240, 240 ),
	"guiEffectsDisabledBackground" : java.awt.Color( 245, 245, 245, 100 ),
	"guiEffectsDisabledLine" : java.awt.Color( 128, 128, 128, 120 ),
	"guiEffectsShadow" : java.awt.Color( 0, 0, 0, 96 ),
	"guiEffectsEdge" : java.awt.Color( 0, 0, 0, 0 ),
	"guiEffectsTroughHighlight" : java.awt.Color( 255, 255, 255 ),
	"guiEffectsTroughShadow" : java.awt.Color( 96, 96, 96 ),
	"propertyDnDPanel" : java.awt.Color( 255, 255, 200 ),
	"prototypeParameter" : java.awt.Color( 255, 255, 200 ),
	"elementDnDPanel" : java.awt.Color( 255, 230, 180 ),
	"elementPrototypeDnDPanel" : java.awt.Color( 255, 255, 255 ),
	"formattedElementViewController" : java.awt.Color( 255, 255, 255 ),
	"response" : java.awt.Color( 255, 230, 180 ),
	"question" : java.awt.Color( 212, 204, 249 ),
	"userDefinedResponse" : java.awt.Color( 255, 230, 180 ),
	"userDefinedQuestion" : java.awt.Color( 212, 204, 249 ),
	"userDefinedQuestionComponent" : java.awt.Color( 255, 230, 180 ),
	"commentForeground" : java.awt.Color( 0, 164, 0 ),
	"variableDnDPanel" : java.awt.Color( 255, 255, 200 ),
	"userDefinedQuestionEditor" : java.awt.Color( 225, 255, 195 ),
	"userDefinedResponseEditor" : java.awt.Color( 255, 255, 210 ),
	"editorHeaderColor" : java.awt.Color( 255, 255, 255 ),
	"behavior" : java.awt.Color( 203, 231, 236 ),
	"behaviorBackground" : java.awt.Color( 255, 255, 255 ),
	"makeSceneEditorBigBackground" : java.awt.Color( 0, 150, 0 ),
	"makeSceneEditorSmallBackground" : java.awt.Color( 0, 150, 0 ),
	"stdErrTextColor" : java.awt.Color( 52, 174, 32 ),
        "mainFontColor" : java.awt.Color(0,0,0),

}


#########################
# Experimental Features
#########################

experimental = 0



####################################
# transfer resource data to Alice
####################################

resourceTransferFile = java.io.File( JAlice.getAliceHomeDirectory(), "resources/common/ResourceTransfer.py" )
execfile( resourceTransferFile.getAbsolutePath() )
