KC=		kotlinc
KFLAG=		-cp
LIB=	lib/programas

all:	\
	$(LIB)/Atomico.class\
	$(LIB)/Struct.class\
	$(LIB)/Union.class\
	ManejadorDatosKt.class\
		
$(LIB)/Atomico.class: $(LIB)/Atomico.kt
	$(KC) $(KFLAG) $(LIB): $(LIB)/Atomico.kt

$(LIB)/Struct.class: $(LIB)/Struct.kt
	$(KC) $(KFLAG) $(LIB): $(LIB)/Struct.kt

$(LIB)/Union.class: $(LIB)/Union.kt
	$(KC) $(KFLAG) $(LIB): $(LIB)/Union.kt

ManejadorDatosKt.class: ManejadorDatos.kt 
	$(KC) $(KFLAG) .:$(LIB) ManejadorDatos.kt
	
clean:
	rm -rf *.class $(LIB)/*.class $(LIB)/META-INF 