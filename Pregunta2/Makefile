KC=		kotlinc
KFLAG=		-cp
LIB=	lib/programas

all:	\
	$(LIB)/Prefijo.class\
	ManejadorBoolKt.class\
		
$(LIB)/Prefijo.class: $(LIB)/Prefijo.kt
	$(KC) $(KFLAG) $(LIB): $(LIB)/Prefijo.kt

ManejadorBoolKt.class: ManejadorBool.kt 
	$(KC) $(KFLAG) .:$(LIB) ManejadorBool.kt
	
clean:
	rm -rf *.class $(LIB)/*.class $(LIB)/META-INF 