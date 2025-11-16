package com.example.appestafa.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appestafa.R // Asegúrate de importar tu R
import com.example.appestafa.data.Scam
import com.example.appestafa.data.ScamDetail
import com.example.appestafa.data.StepItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScamViewModel : ViewModel() {

    private val _scams = MutableStateFlow<List<Scam>>(emptyList())
    val scams: StateFlow<List<Scam>> = _scams.asStateFlow()

    private val _scamDetails = MutableStateFlow<Map<String, ScamDetail>>(emptyMap())

    init {
        loadData()
    }

    // --- CARGA DE DATOS ---
    private fun loadData() {

        // --- LISTA PRINCIPAL (PARA ScamListScreen) ---
        _scams.value = listOf(
            // Vishing (Llamadas)
            Scam(
                id = "ejecutivo_bancario",
                title = "Falso Ejecutivo Bancario",
                summary = "Alerta de 'actividad sospechosa' en tu cuenta.",
                iconResId = R.drawable.ic_banco // ¡NUEVO! Agrega "bank"
            ),
            Scam(
                id = "cuento_tio",
                title = "Falso Familiar en Apuros",
                summary = "Un familiar necesita dinero urgente.",
                iconResId = R.drawable.ic_persona
            ),
            Scam(
                id = "premio_falso_llamada",
                title = "Llamada de Premio Falso",
                summary = "Piden pagar 'impuestos' para un premio.",
                iconResId = R.drawable.ic_premio
            ),
            Scam(
                id = "oferta_trabajo",
                title = "Falsa Oferta de Trabajo",
                summary = "Te ofrecen un gran empleo, pero piden un depósito.",
                iconResId = R.drawable.ic_trabajo // ¡NUEVO! Agrega "work"
            ),
            Scam(
                id = "sim_swapping",
                title = "SIM Swapping",
                summary = "Clonan tu número para robar códigos.",
                iconResId = R.drawable.ic_sim // ¡NUEVO! Agrega "sim_card"
            ),
            // Smishing (SMS)
            Scam(
                id = "alerta_banca_sms",
                title = "Alerta de Banca Falsa (SMS)",
                summary = "Mensaje de 'cuenta bloqueada' con un enlace falso.",
                iconResId = R.drawable.ic_banco
            ),
            Scam(
                id = "paquete_sms",
                title = "Entrega de Paquete Falsa (SMS)",
                summary = "SMS sobre 'pago de aduanas' o 'dirección incorrecta'.",
                iconResId = R.drawable.ic_sms
            ),
            Scam(
                id = "cuenta_falsa_sms",
                title = "Verificación de Cuenta Falsa (SMS)",
                summary = "Falso SMS de Netflix, Amazon, etc. para robar tu clave.",
                iconResId = R.drawable.ic_netflix
            ),
            Scam(
                id = "agencia_falsa_sms",
                title = "Falsa Agencia Gubernamental (SMS)",
                summary = "Te alertan de una 'multa impagada' o 'reembolso'.",
                iconResId = R.drawable.ic_gobierno
            ),
            Scam(
                id = "emergencia_familiar_sms",
                title = "Falso Familiar (SMS)",
                summary = "'Hola mamá, perdí mi teléfono. Escríbeme a este...'." ,
                iconResId = R.drawable.ic_persona
            )
        )

        // --- MAPA DE DETALLES (PARA ScamDetailScreen) ---
        _scamDetails.value = mapOf(
            "ejecutivo_bancario" to ScamDetail(
                id = "ejecutivo_bancario",
                title = "Falso Ejecutivo Bancario",
                type = "Tipo: Llamada telefónica (Vishing)",
                headerImageResId = R.drawable.img_soporte_header, // Reusamos esta imagen
                howItWorks = listOf(
                    StepItem("Recibes una llamada (a menudo automatizada) alertando sobre un 'problema' o 'actividad sospechosa' en tu cuenta.", R.drawable.ic_llamada),
                    StepItem("Te convencen para que proporciones tus claves, datos de tarjeta (CVC) o te instan a transferir para 'cancelar' una compra.", R.drawable.ic_tarjeta),
                    StepItem("En algunos casos, te piden ingresar tu clave en el teclado para capturarla (DTMF).", R.drawable.ic_contrasena)
                ),
                redFlags = listOf(
                    StepItem("Te llaman ellos a ti para hablar de tu cuenta. Un banco NUNCA hace eso.", R.drawable.ic_advertencia),
                    StepItem("Te piden claves, CVC o contraseñas. Un banco NUNCA te pedirá esa información.", R.drawable.ic_contrasena),
                    StepItem("El tono es de urgencia extrema: 'si no lo hace ahora, perderá su dinero'.", R.drawable.ic_urgencia)
                ),
                example = "Recibes una llamada: \"Detectamos un cargo sospechoso de $500. Para cancelarlo, por favor valide su identidad ingresando su clave de cajero en el teclado\".",
                whatToDo = listOf(
                    StepItem("Cuelga de inmediato.", R.drawable.ic_colgar),
                    StepItem("No proporciones NINGÚN dato personal o bancario.", R.drawable.ic_silencio), // ¡NUEVO! Agrega "mic_off"
                    StepItem("Llama TÚ MISMO al número oficial de tu banco (el que está al reverso de tu tarjeta) y consulta si existe tal problema.", R.drawable.ic_banco)
                )
            ),
            "cuento_tio" to ScamDetail(
                id = "cuento_tio",
                title = "Falso Familiar en Apuros",
                type = "Tipo: Llamada telefónica (Vishing)",
                headerImageResId = R.drawable.img_soporte_header,
                howItWorks = listOf(
                    StepItem("El estafador se hace pasar por un familiar (hijo/a, nieto/a) que dice estar en una urgencia (accidente, secuestro, problema legal).", R.drawable.ic_persona),
                    StepItem("A menudo alegan haber perdido su celular y llaman de un número desconocido.", R.drawable.ic_llamada),
                    StepItem("Piden dinero de inmediato y te ruegan que 'no le cuentes a nadie' para no preocupar.", R.drawable.ic_silencio)
                ),
                redFlags = listOf(
                    StepItem("Extrema urgencia y un tono de voz alterado o lloroso para que no reconozcas la voz.", R.drawable.ic_urgencia),
                    StepItem("Te piden explícitamente que no hables con otros familiares.", R.drawable.ic_silencio),
                    StepItem("Solicitan una transferencia inmediata o un depósito.", R.drawable.ic_tarjeta)
                ),
                example = "Llamada: \"¡Abuela, soy yo, tuve un accidente! ¡Necesito $1000 para la grúa pero perdí mi billetera! Por favor, deposítame, pero no le digas a mi mamá que se va a enojar\".",
                whatToDo = listOf(
                    StepItem("Mantén la calma y cuelga la llamada.", R.drawable.ic_colgar),
                    StepItem("Intenta contactar al familiar (al número que SÍ tienes guardado) para verificar la historia.", R.drawable.ic_banco),
                    StepItem("No transfieras dinero bajo presión emocional.", R.drawable.ic_tarjeta)
                )
            ),
            "premio_falso_llamada" to ScamDetail(
                id = "premio_falso_llamada",
                title = "Llamada de Premio Falso",
                type = "Tipo: Llamada telefónica (Vishing)",
                headerImageResId = R.drawable.img_soporte_header,
                howItWorks = listOf(
                    StepItem("Te notifican que has ganado un premio, un viaje, una tarjeta de regalo o has sido 'seleccionado' para una gran oferta.", R.drawable.ic_premio),
                    StepItem("La oferta suena demasiado buena para ser verdad.", R.drawable.ic_advertencia)
                ),
                redFlags = listOf(
                    StepItem("Te solicitan un pago por 'gastos de envío', 'impuestos' o 'tasas administrativas' para reclamar el premio.", R.drawable.ic_tarjeta),
                    StepItem("Te piden información personal o bancaria para 'depositarte' el premio.", R.drawable.ic_contrasena)
                ),
                example = "Llamada: \"¡Felicidades! Ha ganado un crucero por el Caribe. Para reservar sus fechas, solo necesitamos cubrir el costo de impuestos de $150 con su tarjeta de crédito\".",
                whatToDo = listOf(
                    StepItem("Cuelga. Si no participaste en un concurso, no puedes haber ganado.", R.drawable.ic_colgar),
                    StepItem("Nunca pagues dinero para recibir un premio. Los premios legítimos no cobran 'tasas'.", R.drawable.ic_tarjeta),
                    StepItem("No des tu información personal.", R.drawable.ic_silencio)
                )
            ),
            "oferta_trabajo" to ScamDetail(
                id = "oferta_trabajo",
                title = "Falsa Oferta de Trabajo",
                type = "Tipo: Llamada telefónica (Vishing)",
                headerImageResId = R.drawable.img_soporte_header,
                howItWorks = listOf(
                    StepItem("Te ofrecen una oportunidad de empleo con altos ingresos y poca dificultad (ej: 'trabajo desde casa').", R.drawable.ic_trabajo),
                    StepItem("Pueden pedirte que realices una acción inicial, como un depósito o la compra de un producto.", R.drawable.ic_tarjeta)
                ),
                redFlags = listOf(
                    StepItem("Promesas de altos ingresos con mínimo esfuerzo.", R.drawable.ic_premio),
                    StepItem("Te piden dinero para 'asegurar el puesto' o para 'material de capacitación'.", R.drawable.ic_tarjeta),
                    StepItem("La 'entrevista' es muy breve y te contratan de inmediato sin verificar tu experiencia.", R.drawable.ic_persona)
                ),
                example = "Llamada: \"Vimos su perfil en línea y es perfecto para nuestro puesto de gerente regional. El salario es de $5000 al mes. Solo necesitamos un depósito de $200 para el kit de inicio\".",
                whatToDo = listOf(
                    StepItem("Investiga la compañía. Busca su sitio web oficial y reseñas.", R.drawable.ic_denuncia),
                    StepItem("Nunca pagues para obtener un trabajo. Las empresas legítimas no cobran por contratarte.", R.drawable.ic_tarjeta)
                )
            ),
            "sim_swapping" to ScamDetail(
                id = "sim_swapping",
                title = "SIM Swapping",
                type = "Tipo: Varios (Vishing/Smishing)",
                headerImageResId = R.drawable.img_soporte_header,
                howItWorks = listOf(
                    StepItem("El estafador obtiene tus datos personales (robados de otras filtraciones).", R.drawable.ic_contrasena),
                    StepItem("Llama a tu compañía telefónica haciéndose pasar por ti. Reporta tu teléfono como 'robado' o 'dañado'.", R.drawable.ic_llamada),
                    StepItem("Solicitan activar tu número en una nueva tarjeta SIM que ellos controlan.", R.drawable.ic_sim)
                ),
                redFlags = listOf(
                    StepItem("Pierdes la señal de tu teléfono de repente y sin razón aparente, por un tiempo prolongado.", R.drawable.ic_advertencia),
                    StepItem("Recibes alertas de cambios en tus cuentas (email, banco) que no hiciste.", R.drawable.ic_urgencia)
                ),
                example = "De repente, tu teléfono muestra 'Sin Servicio' o 'Solo llamadas de emergencia'. Minutos después, recibes un email de que la contraseña de tu banco ha sido restablecida.",
                whatToDo = listOf(
                    StepItem("Si pierdes la señal, contacta a tu operador telefónico INMEDIATAMENTE desde otro teléfono.", R.drawable.ic_llamada),
                    StepItem("No respondas a SMS o emails pidiendo datos personales.", R.drawable.ic_sms),
                    StepItem("Activa la autenticación de dos factores (2FA) en todas tus cuentas, preferiblemente usando una App (como Google Authenticator) en lugar de SMS.", R.drawable.ic_denuncia)
                )
            ),
            "alerta_banca_sms" to ScamDetail(
                id = "alerta_banca_sms",
                title = "Alerta de Banca Falsa (SMS)",
                type = "Tipo: Mensaje de texto (Smishing)",
                headerImageResId = R.drawable.img_soporte_header,
                howItWorks = listOf(
                    StepItem("Recibes un SMS urgente, supuestamente de tu banco.", R.drawable.ic_sms),
                    StepItem("Alerta de una 'transacción sospechosa', 'cuenta bloqueada' o 'verificación de seguridad'.", R.drawable.ic_advertencia)
                ),
                redFlags = listOf(
                    StepItem("Incluyen un enlace (URL) que lleva a un sitio web FALSO que imita al de tu banco.", R.drawable.ic_link), // ¡NUEVO! Agrega "link"
                    StepItem("El enlace es de un dominio extraño (ej: 'banco-seguridad.xyz').", R.drawable.ic_link),
                    StepItem("Te piden ingresar tus credenciales (usuario, clave) en esa página falsa.", R.drawable.ic_contrasena)
                ),
                example = "SMS: \"(BANCO) Su cuenta ha sido bloqueada por actividad sospechosa. Por favor, verifique su identidad en: [enlace malicioso]\"",
                whatToDo = listOf(
                    StepItem("No hagas clic en el enlace.", R.drawable.ic_link),
                    StepItem("Tu banco NUNCA te pedirá verificar tu cuenta por SMS.", R.drawable.ic_banco),
                    StepItem("Borra el mensaje. Si tienes dudas, entra al sitio de tu banco escribiendo la dirección TÚ MISMO en el navegador o usando la app oficial.", R.drawable.ic_denuncia)
                )
            ),
            "paquete_sms" to ScamDetail(
                id = "paquete_sms",
                title = "Entrega de Paquete Falsa (SMS)",
                type = "Tipo: Mensaje de texto (Smishing)",
                headerImageResId = R.drawable.img_soporte_header,
                howItWorks = listOf(
                    StepItem("Recibes un SMS que parece ser de una empresa de paquetería (Correos, FedEx, UPS, etc.).", R.drawable.ic_sms),
                    StepItem("Informa que hay un problema: 'dirección incorrecta', 'falta de pago de aduanas' o 'paquete retenido'.", R.drawable.ic_advertencia)
                ),
                redFlags = listOf(
                    StepItem("Incluye un enlace (URL) que NO es el sitio web oficial (ej: 'fedex-entrega.xyz').", R.drawable.ic_link),
                    StepItem("Te piden un pago 'pequeño' (ej: $1 o $2) por 'aduanas'. El objetivo es robar los datos de tu tarjeta.", R.drawable.ic_tarjeta),
                    StepItem("El mensaje tiene faltas de ortografía o una redacción extraña.", R.drawable.ic_denuncia)
                ),
                example = "SMS: \"(Correos) Su paquete no se ha podido entregar por falta de pago de aduanas. Pague la tasa de $1.99 para liberarlo: [enlace malicioso]\"",
                whatToDo = listOf(
                    StepItem("No hagas clic en el enlace.", R.drawable.ic_link),
                    StepItem("Si esperas un paquete, rastréalo usando el código en el sitio web OFICIAL de la paquetería.", R.drawable.ic_servicios),
                    StepItem("Nunca ingreses datos bancarios en un enlace que te llegó por SMS.", R.drawable.ic_tarjeta)
                )
            ),
            "cuenta_falsa_sms" to ScamDetail(
                id = "cuenta_falsa_sms",
                title = "Verificación de Cuenta Falsa (SMS)",
                type = "Tipo: Mensaje de texto (Smishing)",
                headerImageResId = R.drawable.img_soporte_header,
                howItWorks = listOf(
                    StepItem("Te notifican que tu cuenta en un servicio popular (Netflix, Amazon, PayPal) está 'suspendida', 'bloqueada' o 'requiere actualización'.", R.drawable.ic_netflix),
                    StepItem("El mensaje incluye un enlace para 'solucionar' el problema.", R.drawable.ic_link)
                ),
                redFlags = listOf(
                    StepItem("El enlace te dirige a una página de inicio de sesión falsa para robar tu usuario y contraseña.", R.drawable.ic_contrasena),
                    StepItem("Nuevamente, la URL del enlace no es la oficial (ej: 'netflix-perfil.com').", R.drawable.ic_link)
                ),
                example = "SMS: \"(Netflix) Su último pago fue rechazado. Para evitar la suspensión de su cuenta, actualice su método de pago aquí: [enlace malicioso]\"",
                whatToDo = listOf(
                    StepItem("No hagas clic en el enlace.", R.drawable.ic_link),
                    StepItem("Si dudas, entra al servicio (Netflix, Amazon) TÚ MISMO desde su app oficial o escribiendo la web en tu navegador.", R.drawable.ic_netflix)
                )
            ),
            "agencia_falsa_sms" to ScamDetail(
                id = "agencia_falsa_sms",
                title = "Falsa Agencia Gubernamental (SMS)",
                type = "Tipo: Mensaje de texto (Smishing)",
                headerImageResId = R.drawable.img_soporte_header,
                howItWorks = listOf(
                    StepItem("El mensaje simula ser de una agencia oficial (Hacienda, Seguridad Social, policía, peajes).", R.drawable.ic_gobierno),
                    StepItem("Te alerta sobre una 'multa impagada', un 'reembolso pendiente' o un 'problema fiscal'.", R.drawable.ic_advertencia)
                ),
                redFlags = listOf(
                    StepItem("Genera miedo para que hagas clic en un enlace y realices un pago o proporciones información personal.", R.drawable.ic_urgencia),
                    StepItem("Las agencias de gobierno casi nunca usan SMS para notificaciones tan críticas.", R.drawable.ic_sms)
                ),
                example = "SMS: \"(Hacienda) Se ha detectado una irregularidad en su declaración. Tiene una multa de $300. Pague ahora para evitar recargos: [enlace malioso]\"",
                whatToDo = listOf(
                    StepItem("No hagas clic ni pagues.", R.drawable.ic_link),
                    StepItem("Contacta a la agencia (si crees que puede ser real) a través de sus canales oficiales para verificar.", R.drawable.ic_gobierno)
                )
            ),
            "emergencia_familiar_sms" to ScamDetail(
                id = "emergencia_familiar_sms",
                title = "Falso Familiar (SMS)",
                type = "Tipo: Mensaje de texto (Smishing)",
                headerImageResId = R.drawable.img_soporte_header,
                howItWorks = listOf(
                    StepItem("Recibes un mensaje de un número desconocido que dice ser tu hijo/a u otro familiar.", R.drawable.ic_persona),
                    StepItem("Alegan que 'perdieron/rompieron su teléfono' y están usando uno temporal.", R.drawable.ic_sms),
                    StepItem("Te piden dinero urgente o que les envíes un WhatsApp a ese número.", R.drawable.ic_tarjeta)
                ),
                redFlags = listOf(
                    StepItem("El tono es de urgencia, pero menos caótico que una llamada, más 'casual'.", R.drawable.ic_urgencia),
                    StepItem("Te piden dinero para algo creíble (pagar una factura, comprar un teléfono nuevo).", R.drawable.ic_tarjeta),
                    StepItem("Intentan llevar la conversación a WhatsApp, donde es más fácil continuar la suplantación.", R.drawable.ic_link)
                ),
                example = "SMS: \"Hola mamá, se me cayó el cel al agua, este es mi número temporal. ¿Me puedes ayudar a pagar una cosa urgente porfa? Escríbeme a WhatsApp.\"",
                whatToDo = listOf(
                    StepItem("No respondas, no agregues a WhatsApp y no envíes dinero.", R.drawable.ic_silencio),
                    StepItem("Intenta contactar a tu familiar a su número de teléfono de SIEMPRE. Si no contesta, llama a otro familiar para verificar.", R.drawable.ic_llamada)
                )
            )
        )
    }

    /**
     * Busca el detalle por su ID.
     */
    fun getScamDetailById(id: String): ScamDetail? {
        return _scamDetails.value[id]
    }
}