package com.javax0.djcproxy;
public class PROXY$CLASS$A extends com.javax0.djcproxy.ProxyFactoryTest.A implements com.javax0.djcproxy.ProxySetter{
private Object PROXY$OBJECT = null;
private com.javax0.djcproxy.MethodInterceptor PROXY$INTERCEPTOR = null;
public void setPROXY$OBJECT(Object PROXY$OBJECT){ this.PROXY$OBJECT = PROXY$OBJECT; }
public void setPROXY$INTERCEPTOR(com.javax0.djcproxy.MethodInterceptor PROXY$INTERCEPTOR){ this.PROXY$INTERCEPTOR = PROXY$INTERCEPTOR; }
@Override public int method(){
try{return (int)PROXY$INTERCEPTOR.intercept(PROXY$OBJECT, PROXY$INTERCEPTOR.getClass().getMethod("method", new Class[]{}), new Object[]{});}catch(Exception e){throw new RuntimeException();}}
@Override public boolean equals(java.lang.Object p1){
try{return (boolean)PROXY$INTERCEPTOR.intercept(PROXY$OBJECT, PROXY$INTERCEPTOR.getClass().getMethod("equals", new Class[]{java.lang.Object.class}), new Object[]{p1});}catch(Exception e){throw new RuntimeException();}}
@Override public java.lang.String toString(){
try{return (java.lang.String)PROXY$INTERCEPTOR.intercept(PROXY$OBJECT, PROXY$INTERCEPTOR.getClass().getMethod("toString", new Class[]{}), new Object[]{});}catch(Exception e){throw new RuntimeException();}}
@Override public int hashCode(){
try{return (int)PROXY$INTERCEPTOR.intercept(PROXY$OBJECT, PROXY$INTERCEPTOR.getClass().getMethod("hashCode", new Class[]{}), new Object[]{});}catch(Exception e){throw new RuntimeException();}}
}
