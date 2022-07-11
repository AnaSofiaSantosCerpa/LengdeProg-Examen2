KC=		kotlinc
KFLAG=		-cp
LIB=	lib/programas

all:	\
	$(LIB)/FR.class\
	$(LIB)/FI.class\
	FamiliaFuncKt.class\
		
$(LIB)/FR.class: $(LIB)/FR.kt
	$(KC) $(KFLAG) $(LIB): $(LIB)/FR.kt

$(LIB)/FI.class: $(LIB)/FI.kt
	$(KC) $(KFLAG) $(LIB): $(LIB)/FI.kt

FamiliaFuncKt.class: FamiliaFunc.kt 
	$(KC) $(KFLAG) .:$(LIB) FamiliaFunc.kt
	
clean:
	rm -rf *.class $(LIB)/*.class $(LIB)/META-INF 