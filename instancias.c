#include<stdio.h>
#include<stdlib.h>
#include<time.h>
FILE *arq;
int main(){
	srand(time(NULL));
	int i,x,y;
	arq=fopen("Tam130.3.txt","w");
	if(arq!=NULL){
		fprintf(arq,"130\n");
		for(i=0;i<130;i++){
			x=rand()%4000;
			y=rand()%4000;
			fprintf(arq,"%i %i\n",x,y);		
		}
	}
	fclose(arq);
	
	return 0;
}
