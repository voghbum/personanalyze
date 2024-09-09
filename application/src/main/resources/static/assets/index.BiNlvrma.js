import{M as qe,N as Ge,O as se,P as be,p as W,a as we,e as Ue,g as Se,n as k,Q as Ye,R as wt,r as D,S as q,o as pe,T as V,U as H,W as St,X as Le,Y as fe,Z as de,$ as Te,a0 as Ee,a1 as pt,a2 as ve,a3 as Ne,a4 as Be,w as I,a5 as Re,a6 as Et,y as xe,a7 as Ke,a8 as xt,a9 as ce,aa as Xe,ab as Ve,ac as z,ad as kt,s as X,ae as Ct,af as Ot,ag as Qe,ah as Pt,ai as At,aj as _t,ak as Lt,f as Tt,al as Nt,am as Bt,h as Rt,an as Vt,u as Mt,t as Ft,ao as jt,ap as It,aq as Ht,ar as $t,as as Dt,q as Wt,v,at as zt,au as qt,av as Gt,aw as Ut,ax as Yt,F as Kt,ay as Xt,az as Qt,z as Ze,A as Je,B as et,C as p,aA as Zt,aB as Me,G as K,aC as Z,H as J,I as Jt,aD as en}from"./main.BEXWJvBX.js";const ae=new WeakMap;function tn(e,n){Object.keys(n).forEach(t=>{if(qe(t)){const o=Ge(t),a=ae.get(e);if(n[t]==null)a==null||a.forEach(i=>{const[s,r]=i;s===o&&(e.removeEventListener(o,r),a.delete(i))});else if(!a||![...a].some(i=>i[0]===o&&i[1]===n[t])){e.addEventListener(o,n[t]);const i=a||new Set;i.add([o,n[t]]),ae.has(e)||ae.set(e,i)}}else n[t]==null?e.removeAttribute(t):e.setAttribute(t,n[t])})}function nn(e,n){Object.keys(n).forEach(t=>{if(qe(t)){const o=Ge(t),a=ae.get(e);a==null||a.forEach(i=>{const[s,r]=i;s===o&&(e.removeEventListener(o,r),a.delete(i))})}else e.removeAttribute(t)})}function tt(e){if(typeof e.getRootNode!="function"){for(;e.parentNode;)e=e.parentNode;return e!==document?null:document}const n=e.getRootNode();return n!==document&&n.getRootNode({composed:!0})!==document?null:n}function on(e){let n=arguments.length>1&&arguments[1]!==void 0?arguments[1]:!1;for(;e;){if(n?an(e):ke(e))return e;e=e.parentElement}return document.scrollingElement}function ie(e,n){const t=[];if(n&&e&&!n.contains(e))return t;for(;e&&(ke(e)&&t.push(e),e!==n);)e=e.parentElement;return t}function ke(e){if(!e||e.nodeType!==Node.ELEMENT_NODE)return!1;const n=window.getComputedStyle(e);return n.overflowY==="scroll"||n.overflowY==="auto"&&e.scrollHeight>e.clientHeight}function an(e){if(!e||e.nodeType!==Node.ELEMENT_NODE)return!1;const n=window.getComputedStyle(e);return["scroll","auto"].includes(n.overflowY)}function rn(e){for(;e;){if(window.getComputedStyle(e).position==="fixed")return!0;e=e.offsetParent}return!1}const nt=se.reduce((e,n)=>(e[n]={type:[Boolean,String,Number],default:!1},e),{}),ot=se.reduce((e,n)=>{const t="offset"+be(n);return e[t]={type:[String,Number],default:null},e},{}),at=se.reduce((e,n)=>{const t="order"+be(n);return e[t]={type:[String,Number],default:null},e},{}),Fe={col:Object.keys(nt),offset:Object.keys(ot),order:Object.keys(at)};function ln(e,n,t){let o=e;if(!(t==null||t===!1)){if(n){const a=n.replace(e,"");o+=`-${a}`}return e==="col"&&(o="v-"+o),e==="col"&&(t===""||t===!0)||(o+=`-${t}`),o.toLowerCase()}}const sn=["auto","start","end","center","baseline","stretch"],cn=W({cols:{type:[Boolean,String,Number],default:!1},...nt,offset:{type:[String,Number],default:null},...ot,order:{type:[String,Number],default:null},...at,alignSelf:{type:String,default:null,validator:e=>sn.includes(e)},...we(),...Ue()},"VCol"),ee=Se()({name:"VCol",props:cn(),setup(e,n){let{slots:t}=n;const o=k(()=>{const a=[];let i;for(i in Fe)Fe[i].forEach(r=>{const u=e[r],d=ln(i,r,u);d&&a.push(d)});const s=a.some(r=>r.startsWith("v-col-"));return a.push({"v-col":!s||!e.cols,[`v-col-${e.cols}`]:e.cols,[`offset-${e.offset}`]:e.offset,[`order-${e.order}`]:e.order,[`align-self-${e.alignSelf}`]:e.alignSelf}),a});return()=>{var a;return Ye(e.tag,{class:[o.value,e.class],style:e.style},(a=t.default)==null?void 0:a.call(t))}}}),Ce=["start","end","center"],rt=["space-between","space-around","space-evenly"];function Oe(e,n){return se.reduce((t,o)=>{const a=e+be(o);return t[a]=n(),t},{})}const un=[...Ce,"baseline","stretch"],it=e=>un.includes(e),lt=Oe("align",()=>({type:String,default:null,validator:it})),fn=[...Ce,...rt],st=e=>fn.includes(e),ct=Oe("justify",()=>({type:String,default:null,validator:st})),dn=[...Ce,...rt,"stretch"],ut=e=>dn.includes(e),ft=Oe("alignContent",()=>({type:String,default:null,validator:ut})),je={align:Object.keys(lt),justify:Object.keys(ct),alignContent:Object.keys(ft)},vn={align:"align",justify:"justify",alignContent:"align-content"};function mn(e,n,t){let o=vn[e];if(t!=null){if(n){const a=n.replace(e,"");o+=`-${a}`}return o+=`-${t}`,o.toLowerCase()}}const yn=W({dense:Boolean,noGutters:Boolean,align:{type:String,default:null,validator:it},...lt,justify:{type:String,default:null,validator:st},...ct,alignContent:{type:String,default:null,validator:ut},...ft,...we(),...Ue()},"VRow"),gn=Se()({name:"VRow",props:yn(),setup(e,n){let{slots:t}=n;const o=k(()=>{const a=[];let i;for(i in je)je[i].forEach(s=>{const r=e[s],u=mn(i,s,r);u&&a.push(u)});return a.push({"v-row--no-gutters":e.noGutters,"v-row--dense":e.dense,[`align-${e.align}`]:e.align,[`justify-${e.justify}`]:e.justify,[`align-content-${e.alignContent}`]:e.alignContent}),a});return()=>{var a;return Ye(e.tag,{class:["v-row",o.value,e.class],style:e.style},(a=t.default)==null?void 0:a.call(t))}}}),hn="/assets/logo.DtuIbWiQ.png",Ie=wt("v-kbd");function me(e,n){return{x:e.x+n.x,y:e.y+n.y}}function bn(e,n){return{x:e.x-n.x,y:e.y-n.y}}function He(e,n){if(e.side==="top"||e.side==="bottom"){const{side:t,align:o}=e,a=o==="left"?0:o==="center"?n.width/2:o==="right"?n.width:o,i=t==="top"?0:t==="bottom"?n.height:t;return me({x:a,y:i},n)}else if(e.side==="left"||e.side==="right"){const{side:t,align:o}=e,a=t==="left"?0:t==="right"?n.width:t,i=o==="top"?0:o==="center"?n.height/2:o==="bottom"?n.height:o;return me({x:a,y:i},n)}return me({x:n.width/2,y:n.height/2},n)}const dt={static:pn,connected:xn},wn=W({locationStrategy:{type:[String,Function],default:"static",validator:e=>typeof e=="function"||e in dt},location:{type:String,default:"bottom"},origin:{type:String,default:"auto"},offset:[Number,String,Array]},"VOverlay-location-strategies");function Sn(e,n){const t=D({}),o=D();q&&pe(()=>!!(n.isActive.value&&e.locationStrategy),i=>{var s,r;V(()=>e.locationStrategy,i),H(()=>{window.removeEventListener("resize",a),o.value=void 0}),window.addEventListener("resize",a,{passive:!0}),typeof e.locationStrategy=="function"?o.value=(s=e.locationStrategy(n,e,t))==null?void 0:s.updateLocation:o.value=(r=dt[e.locationStrategy](n,e,t))==null?void 0:r.updateLocation});function a(i){var s;(s=o.value)==null||s.call(o,i)}return{contentStyles:t,updateLocation:o}}function pn(){}function En(e,n){const t=Et(e);return n?t.x+=parseFloat(e.style.right||0):t.x-=parseFloat(e.style.left||0),t.y-=parseFloat(e.style.top||0),t}function xn(e,n,t){(Array.isArray(e.target.value)||rn(e.target.value))&&Object.assign(t.value,{position:"fixed",top:0,[e.isRtl.value?"right":"left"]:0});const{preferredAnchor:a,preferredOrigin:i}=St(()=>{const m=Le(n.location,e.isRtl.value),f=n.origin==="overlap"?m:n.origin==="auto"?fe(m):Le(n.origin,e.isRtl.value);return m.side===f.side&&m.align===de(f).align?{preferredAnchor:Te(m),preferredOrigin:Te(f)}:{preferredAnchor:m,preferredOrigin:f}}),[s,r,u,d]=["minWidth","minHeight","maxWidth","maxHeight"].map(m=>k(()=>{const f=parseFloat(n[m]);return isNaN(f)?1/0:f})),c=k(()=>{if(Array.isArray(n.offset))return n.offset;if(typeof n.offset=="string"){const m=n.offset.split(" ").map(parseFloat);return m.length<2&&m.push(0),m}return typeof n.offset=="number"?[n.offset,0]:[0,0]});let S=!1;const O=new ResizeObserver(()=>{S&&C()});V([e.target,e.contentEl],(m,f)=>{let[A,_]=m,[E,w]=f;E&&!Array.isArray(E)&&O.unobserve(E),A&&!Array.isArray(A)&&O.observe(A),w&&O.unobserve(w),_&&O.observe(_)},{immediate:!0}),H(()=>{O.disconnect()});function C(){if(S=!1,requestAnimationFrame(()=>S=!0),!e.target.value||!e.contentEl.value)return;const m=pt(e.target.value),f=En(e.contentEl.value,e.isRtl.value),A=ie(e.contentEl.value),_=12;A.length||(A.push(document.documentElement),e.contentEl.value.style.top&&e.contentEl.value.style.left||(f.x-=parseFloat(document.documentElement.style.getPropertyValue("--v-body-scroll-x")||0),f.y-=parseFloat(document.documentElement.style.getPropertyValue("--v-body-scroll-y")||0)));const E=A.reduce((x,b)=>{const y=b.getBoundingClientRect(),h=new ve({x:b===document.documentElement?0:y.x,y:b===document.documentElement?0:y.y,width:b.clientWidth,height:b.clientHeight});return x?new ve({x:Math.max(x.left,h.left),y:Math.max(x.top,h.top),width:Math.min(x.right,h.right)-Math.max(x.left,h.left),height:Math.min(x.bottom,h.bottom)-Math.max(x.top,h.top)}):h},void 0);E.x+=_,E.y+=_,E.width-=_*2,E.height-=_*2;let w={anchor:a.value,origin:i.value};function T(x){const b=new ve(f),y=He(x.anchor,m),h=He(x.origin,b);let{x:F,y:j}=bn(y,h);switch(x.anchor.side){case"top":j-=c.value[0];break;case"bottom":j+=c.value[0];break;case"left":F-=c.value[0];break;case"right":F+=c.value[0];break}switch(x.anchor.align){case"top":j-=c.value[1];break;case"bottom":j+=c.value[1];break;case"left":F-=c.value[1];break;case"right":F+=c.value[1];break}return b.x+=F,b.y+=j,b.width=Math.min(b.width,u.value),b.height=Math.min(b.height,d.value),{overflows:Be(b,E),x:F,y:j}}let B=0,M=0;const P={x:0,y:0},l={x:!1,y:!1};let N=-1;for(;!(N++>10);){const{x,y:b,overflows:y}=T(w);B+=x,M+=b,f.x+=x,f.y+=b;{const h=Ne(w.anchor),F=y.x.before||y.x.after,j=y.y.before||y.y.after;let G=!1;if(["x","y"].forEach(L=>{if(L==="x"&&F&&!l.x||L==="y"&&j&&!l.y){const $={anchor:{...w.anchor},origin:{...w.origin}},U=L==="x"?h==="y"?de:fe:h==="y"?fe:de;$.anchor=U($.anchor),$.origin=U($.origin);const{overflows:Y}=T($);(Y[L].before<=y[L].before&&Y[L].after<=y[L].after||Y[L].before+Y[L].after<(y[L].before+y[L].after)/2)&&(w=$,G=l[L]=!0)}}),G)continue}y.x.before&&(B+=y.x.before,f.x+=y.x.before),y.x.after&&(B-=y.x.after,f.x-=y.x.after),y.y.before&&(M+=y.y.before,f.y+=y.y.before),y.y.after&&(M-=y.y.after,f.y-=y.y.after);{const h=Be(f,E);P.x=E.width-h.x.before-h.x.after,P.y=E.height-h.y.before-h.y.after,B+=h.x.before,f.x+=h.x.before,M+=h.y.before,f.y+=h.y.before}break}const ue=Ne(w.anchor);return Object.assign(t.value,{"--v-overlay-anchor-origin":`${w.anchor.side} ${w.anchor.align}`,transformOrigin:`${w.origin.side} ${w.origin.align}`,top:I(ye(M)),left:e.isRtl.value?void 0:I(ye(B)),right:e.isRtl.value?I(ye(-B)):void 0,minWidth:I(ue==="y"?Math.min(s.value,m.width):s.value),maxWidth:I($e(Re(P.x,s.value===1/0?0:s.value,u.value))),maxHeight:I($e(Re(P.y,r.value===1/0?0:r.value,d.value)))}),{available:P,contentBox:f}}return V(()=>[a.value,i.value,n.offset,n.minWidth,n.minHeight,n.maxWidth,n.maxHeight],()=>C()),Ee(()=>{const m=C();if(!m)return;const{available:f,contentBox:A}=m;A.height>f.y&&requestAnimationFrame(()=>{C(),requestAnimationFrame(()=>{C()})})}),{updateLocation:C}}function ye(e){return Math.round(e*devicePixelRatio)/devicePixelRatio}function $e(e){return Math.ceil(e*devicePixelRatio)/devicePixelRatio}let ge=!0;const le=[];function kn(e){!ge||le.length?(le.push(e),he()):(ge=!1,e(),he())}let De=-1;function he(){cancelAnimationFrame(De),De=requestAnimationFrame(()=>{const e=le.shift();e&&e(),le.length?he():ge=!0})}const re={none:null,close:Pn,block:An,reposition:_n},Cn=W({scrollStrategy:{type:[String,Function],default:"block",validator:e=>typeof e=="function"||e in re}},"VOverlay-scroll-strategies");function On(e,n){if(!q)return;let t;xe(async()=>{t==null||t.stop(),n.isActive.value&&e.scrollStrategy&&(t=Ke(),await new Promise(o=>setTimeout(o)),t.active&&t.run(()=>{var o;typeof e.scrollStrategy=="function"?e.scrollStrategy(n,e,t):(o=re[e.scrollStrategy])==null||o.call(re,n,e,t)}))}),H(()=>{t==null||t.stop()})}function Pn(e){function n(t){e.isActive.value=!1}vt(e.targetEl.value??e.contentEl.value,n)}function An(e,n){var s;const t=(s=e.root.value)==null?void 0:s.offsetParent,o=[...new Set([...ie(e.targetEl.value,n.contained?t:void 0),...ie(e.contentEl.value,n.contained?t:void 0)])].filter(r=>!r.classList.contains("v-overlay-scroll-blocked")),a=window.innerWidth-document.documentElement.offsetWidth,i=(r=>ke(r)&&r)(t||document.documentElement);i&&e.root.value.classList.add("v-overlay--scroll-blocked"),o.forEach((r,u)=>{r.style.setProperty("--v-body-scroll-x",I(-r.scrollLeft)),r.style.setProperty("--v-body-scroll-y",I(-r.scrollTop)),r!==document.documentElement&&r.style.setProperty("--v-scrollbar-offset",I(a)),r.classList.add("v-overlay-scroll-blocked")}),H(()=>{o.forEach((r,u)=>{const d=parseFloat(r.style.getPropertyValue("--v-body-scroll-x")),c=parseFloat(r.style.getPropertyValue("--v-body-scroll-y")),S=r.style.scrollBehavior;r.style.scrollBehavior="auto",r.style.removeProperty("--v-body-scroll-x"),r.style.removeProperty("--v-body-scroll-y"),r.style.removeProperty("--v-scrollbar-offset"),r.classList.remove("v-overlay-scroll-blocked"),r.scrollLeft=-d,r.scrollTop=-c,r.style.scrollBehavior=S}),i&&e.root.value.classList.remove("v-overlay--scroll-blocked")})}function _n(e,n,t){let o=!1,a=-1,i=-1;function s(r){kn(()=>{var c,S;const u=performance.now();(S=(c=e.updateLocation).value)==null||S.call(c,r),o=(performance.now()-u)/(1e3/60)>2})}i=(typeof requestIdleCallback>"u"?r=>r():requestIdleCallback)(()=>{t.run(()=>{vt(e.targetEl.value??e.contentEl.value,r=>{o?(cancelAnimationFrame(a),a=requestAnimationFrame(()=>{a=requestAnimationFrame(()=>{s(r)})})):s(r)})})}),H(()=>{typeof cancelIdleCallback<"u"&&cancelIdleCallback(i),cancelAnimationFrame(a)})}function vt(e,n){const t=[document,...ie(e)];t.forEach(o=>{o.addEventListener("scroll",n,{passive:!0})}),H(()=>{t.forEach(o=>{o.removeEventListener("scroll",n)})})}const Ln=Symbol.for("vuetify:v-menu"),Tn=W({closeDelay:[Number,String],openDelay:[Number,String]},"delay");function Nn(e,n){let t=()=>{};function o(s){t==null||t();const r=Number(s?e.openDelay:e.closeDelay);return new Promise(u=>{t=xt(r,()=>{n==null||n(s),u(s)})})}function a(){return o(!0)}function i(){return o(!1)}return{clearDelay:t,runOpenDelay:a,runCloseDelay:i}}const Bn=W({target:[String,Object],activator:[String,Object],activatorProps:{type:Object,default:()=>({})},openOnClick:{type:Boolean,default:void 0},openOnHover:Boolean,openOnFocus:{type:Boolean,default:void 0},closeOnContentClick:Boolean,...Tn()},"VOverlay-activator");function Rn(e,n){let{isActive:t,isTop:o,contentEl:a}=n;const i=ce("useActivator"),s=D();let r=!1,u=!1,d=!0;const c=k(()=>e.openOnFocus||e.openOnFocus==null&&e.openOnHover),S=k(()=>e.openOnClick||e.openOnClick==null&&!e.openOnHover&&!c.value),{runOpenDelay:O,runCloseDelay:C}=Nn(e,l=>{l===(e.openOnHover&&r||c.value&&u)&&!(e.openOnHover&&t.value&&!o.value)&&(t.value!==l&&(d=!0),t.value=l)}),m=D(),f={onClick:l=>{l.stopPropagation(),s.value=l.currentTarget||l.target,t.value||(m.value=[l.clientX,l.clientY]),t.value=!t.value},onMouseenter:l=>{var N;(N=l.sourceCapabilities)!=null&&N.firesTouchEvents||(r=!0,s.value=l.currentTarget||l.target,O())},onMouseleave:l=>{r=!1,C()},onFocus:l=>{kt(l.target,":focus-visible")!==!1&&(u=!0,l.stopPropagation(),s.value=l.currentTarget||l.target,O())},onBlur:l=>{u=!1,l.stopPropagation(),C()}},A=k(()=>{const l={};return S.value&&(l.onClick=f.onClick),e.openOnHover&&(l.onMouseenter=f.onMouseenter,l.onMouseleave=f.onMouseleave),c.value&&(l.onFocus=f.onFocus,l.onBlur=f.onBlur),l}),_=k(()=>{const l={};if(e.openOnHover&&(l.onMouseenter=()=>{r=!0,O()},l.onMouseleave=()=>{r=!1,C()}),c.value&&(l.onFocusin=()=>{u=!0,O()},l.onFocusout=()=>{u=!1,C()}),e.closeOnContentClick){const N=Xe(Ln,null);l.onClick=()=>{t.value=!1,N==null||N.closeParents()}}return l}),E=k(()=>{const l={};return e.openOnHover&&(l.onMouseenter=()=>{d&&(r=!0,d=!1,O())},l.onMouseleave=()=>{r=!1,C()}),l});V(o,l=>{var N;l&&(e.openOnHover&&!r&&(!c.value||!u)||c.value&&!u&&(!e.openOnHover||!r))&&!((N=a.value)!=null&&N.contains(document.activeElement))&&(t.value=!1)}),V(t,l=>{l||setTimeout(()=>{m.value=void 0})},{flush:"post"});const w=Ve();xe(()=>{w.value&&Ee(()=>{s.value=w.el})});const T=Ve(),B=k(()=>e.target==="cursor"&&m.value?m.value:T.value?T.el:mt(e.target,i)||s.value),M=k(()=>Array.isArray(B.value)?void 0:B.value);let P;return V(()=>!!e.activator,l=>{l&&q?(P=Ke(),P.run(()=>{Vn(e,i,{activatorEl:s,activatorEvents:A})})):P&&P.stop()},{flush:"post",immediate:!0}),H(()=>{P==null||P.stop()}),{activatorEl:s,activatorRef:w,target:B,targetEl:M,targetRef:T,activatorEvents:A,contentEvents:_,scrimEvents:E}}function Vn(e,n,t){let{activatorEl:o,activatorEvents:a}=t;V(()=>e.activator,(u,d)=>{if(d&&u!==d){const c=r(d);c&&s(c)}u&&Ee(()=>i())},{immediate:!0}),V(()=>e.activatorProps,()=>{i()}),H(()=>{s()});function i(){let u=arguments.length>0&&arguments[0]!==void 0?arguments[0]:r(),d=arguments.length>1&&arguments[1]!==void 0?arguments[1]:e.activatorProps;u&&tn(u,z(a.value,d))}function s(){let u=arguments.length>0&&arguments[0]!==void 0?arguments[0]:r(),d=arguments.length>1&&arguments[1]!==void 0?arguments[1]:e.activatorProps;u&&nn(u,z(a.value,d))}function r(){let u=arguments.length>0&&arguments[0]!==void 0?arguments[0]:e.activator;const d=mt(u,n);return o.value=(d==null?void 0:d.nodeType)===Node.ELEMENT_NODE?d:void 0,o.value}}function mt(e,n){var o,a;if(!e)return;let t;if(e==="parent"){let i=(a=(o=n==null?void 0:n.proxy)==null?void 0:o.$el)==null?void 0:a.parentNode;for(;i!=null&&i.hasAttribute("data-no-activator");)i=i.parentNode;t=i}else typeof e=="string"?t=document.querySelector(e):"$el"in e?t=e.$el:t=e;return t}function Mn(){if(!q)return X(!1);const{ssr:e}=Ct();if(e){const n=X(!1);return Ot(()=>{n.value=!0}),n}else return X(!0)}const Fn=W({eager:Boolean},"lazy");function jn(e,n){const t=X(!1),o=k(()=>t.value||e.eager||n.value);V(n,()=>t.value=!0);function a(){e.eager||(t.value=!1)}return{isBooted:t,hasContent:o,onAfterLeave:a}}function In(){const n=ce("useScopeId").vnode.scopeId;return{scopeId:n?{[n]:""}:void 0}}const We=Symbol.for("vuetify:stack"),te=Qe([]);function Hn(e,n,t){const o=ce("useStack"),a=!t,i=Xe(We,void 0),s=Qe({activeChildren:new Set});Pt(We,s);const r=X(+n.value);pe(e,()=>{var S;const c=(S=te.at(-1))==null?void 0:S[1];r.value=c?c+10:+n.value,a&&te.push([o.uid,r.value]),i==null||i.activeChildren.add(o.uid),H(()=>{if(a){const O=At(te).findIndex(C=>C[0]===o.uid);te.splice(O,1)}i==null||i.activeChildren.delete(o.uid)})});const u=X(!0);a&&xe(()=>{var S;const c=((S=te.at(-1))==null?void 0:S[0])===o.uid;setTimeout(()=>u.value=c)});const d=k(()=>!s.activeChildren.size);return{globalTop:_t(u),localTop:d,stackStyles:k(()=>({zIndex:r.value}))}}function $n(e){return{teleportTarget:k(()=>{const t=e();if(t===!0||!q)return;const o=t===!1?document.body:typeof t=="string"?document.querySelector(t):t;if(o==null)return;let a=[...o.children].find(i=>i.matches(".v-overlay-container"));return a||(a=document.createElement("div"),a.className="v-overlay-container",o.appendChild(a)),a})}}function Dn(){return!0}function yt(e,n,t){if(!e||gt(e,t)===!1)return!1;const o=tt(n);if(typeof ShadowRoot<"u"&&o instanceof ShadowRoot&&o.host===e.target)return!1;const a=(typeof t.value=="object"&&t.value.include||(()=>[]))();return a.push(n),!a.some(i=>i==null?void 0:i.contains(e.target))}function gt(e,n){return(typeof n.value=="object"&&n.value.closeConditional||Dn)(e)}function Wn(e,n,t){const o=typeof t.value=="function"?t.value:t.value.handler;e.shadowTarget=e.target,n._clickOutside.lastMousedownWasOutside&&yt(e,n,t)&&setTimeout(()=>{gt(e,t)&&o&&o(e)},0)}function ze(e,n){const t=tt(e);n(document),typeof ShadowRoot<"u"&&t instanceof ShadowRoot&&n(t)}const zn={mounted(e,n){const t=a=>Wn(a,e,n),o=a=>{e._clickOutside.lastMousedownWasOutside=yt(a,e,n)};ze(e,a=>{a.addEventListener("click",t,!0),a.addEventListener("mousedown",o,!0)}),e._clickOutside||(e._clickOutside={lastMousedownWasOutside:!1}),e._clickOutside[n.instance.$.uid]={onClick:t,onMousedown:o}},beforeUnmount(e,n){e._clickOutside&&(ze(e,t=>{var i;if(!t||!((i=e._clickOutside)!=null&&i[n.instance.$.uid]))return;const{onClick:o,onMousedown:a}=e._clickOutside[n.instance.$.uid];t.removeEventListener("click",o,!0),t.removeEventListener("mousedown",a,!0)}),delete e._clickOutside[n.instance.$.uid])}};function qn(e){const{modelValue:n,color:t,...o}=e;return v(Xt,{name:"fade-transition",appear:!0},{default:()=>[e.modelValue&&v("div",z({class:["v-overlay__scrim",e.color.backgroundColorClasses.value],style:e.color.backgroundColorStyles.value},o),null)]})}const Gn=W({absolute:Boolean,attach:[Boolean,String,Object],closeOnBack:{type:Boolean,default:!0},contained:Boolean,contentClass:null,contentProps:null,disabled:Boolean,opacity:[Number,String],noClickAnimation:Boolean,modelValue:Boolean,persistent:Boolean,scrim:{type:[Boolean,String],default:!0},zIndex:{type:[Number,String],default:2e3},...Bn(),...we(),...Lt(),...Fn(),...wn(),...Cn(),...Tt(),...Nt()},"VOverlay"),ne=Se()({name:"VOverlay",directives:{ClickOutside:zn},inheritAttrs:!1,props:{_disableGlobalStack:Boolean,...Gn()},emits:{"click:outside":e=>!0,"update:modelValue":e=>!0,afterEnter:()=>!0,afterLeave:()=>!0},setup(e,n){let{slots:t,attrs:o,emit:a}=n;const i=ce("VOverlay"),s=D(),r=D(),u=D(),d=Bt(e,"modelValue"),c=k({get:()=>d.value,set:g=>{g&&e.disabled||(d.value=g)}}),{themeClasses:S}=Rt(e),{rtlClasses:O,isRtl:C}=Vt(),{hasContent:m,onAfterLeave:f}=jn(e,c),A=Mt(k(()=>typeof e.scrim=="string"?e.scrim:null)),{globalTop:_,localTop:E,stackStyles:w}=Hn(c,Ft(e,"zIndex"),e._disableGlobalStack),{activatorEl:T,activatorRef:B,target:M,targetEl:P,targetRef:l,activatorEvents:N,contentEvents:ue,scrimEvents:x}=Rn(e,{isActive:c,isTop:E,contentEl:u}),{teleportTarget:b}=$n(()=>{var Q,Ae,_e;const g=e.attach||e.contained;if(g)return g;const R=((Q=T==null?void 0:T.value)==null?void 0:Q.getRootNode())||((_e=(Ae=i.proxy)==null?void 0:Ae.$el)==null?void 0:_e.getRootNode());return R instanceof ShadowRoot?R:!1}),{dimensionStyles:y}=jt(e),h=Mn(),{scopeId:F}=In();V(()=>e.disabled,g=>{g&&(c.value=!1)});const{contentStyles:j,updateLocation:G}=Sn(e,{isRtl:C,contentEl:u,target:M,isActive:c});On(e,{root:s,contentEl:u,targetEl:P,isActive:c,updateLocation:G});function L(g){a("click:outside",g),e.persistent?oe():c.value=!1}function $(g){return c.value&&_.value&&(!e.scrim||g.target===r.value||g instanceof MouseEvent&&g.shadowTarget===r.value)}q&&V(c,g=>{g?window.addEventListener("keydown",U):window.removeEventListener("keydown",U)},{immediate:!0}),It(()=>{q&&window.removeEventListener("keydown",U)});function U(g){var R,Q;g.key==="Escape"&&_.value&&(e.persistent?oe():(c.value=!1,(R=u.value)!=null&&R.contains(document.activeElement)&&((Q=T.value)==null||Q.focus())))}const Y=Ht();pe(()=>e.closeOnBack,()=>{Qt(Y,g=>{_.value&&c.value?(g(!1),e.persistent?oe():c.value=!1):g()})});const Pe=D();V(()=>c.value&&(e.absolute||e.contained)&&b.value==null,g=>{if(g){const R=on(s.value);R&&R!==document.scrollingElement&&(Pe.value=R.scrollTop)}});function oe(){e.noClickAnimation||u.value&&$t(u.value,[{transformOrigin:"center"},{transform:"scale(1.03)"},{transformOrigin:"center"}],{duration:150,easing:Dt})}function ht(){a("afterEnter")}function bt(){f(),a("afterLeave")}return Wt(()=>{var g;return v(Kt,null,[(g=t.activator)==null?void 0:g.call(t,{isActive:c.value,targetRef:l,props:z({ref:B},N.value,e.activatorProps)}),h.value&&m.value&&v(zt,{disabled:!b.value,to:b.value},{default:()=>[v("div",z({class:["v-overlay",{"v-overlay--absolute":e.absolute||e.contained,"v-overlay--active":c.value,"v-overlay--contained":e.contained},S.value,O.value,e.class],style:[w.value,{"--v-overlay-opacity":e.opacity,top:I(Pe.value)},e.style],ref:s},F,o),[v(qn,z({color:A,modelValue:c.value&&!!e.scrim,ref:r},x.value),null),v(qt,{appear:!0,persisted:!0,transition:e.transition,target:M.value,onAfterEnter:ht,onAfterLeave:bt},{default:()=>{var R;return[Gt(v("div",z({ref:u,class:["v-overlay__content",e.contentClass],style:[y.value,j.value]},ue.value,e.contentProps),[(R=t.default)==null?void 0:R.call(t,{isActive:c})]),[[Ut,c.value],[Yt("click-outside"),{handler:L,closeConditional:$,include:()=>[T.value]}]])]}})])]})])}),{activatorEl:T,scrimEl:r,target:M,animateClick:oe,contentEl:u,globalTop:_,localTop:E,updateLocation:G}}}),Un={class:"text-subtitle-1"},Yn=Ze({__name:"HelloWorld",setup(e){return(n,t)=>(Je(),et(en,{class:"fill-height"},{default:p(()=>[v(Zt,{class:"align-centerfill-height mx-auto","max-width":"900"},{default:p(()=>[v(Me,{class:"mb-4",height:"150",src:hn}),t[6]||(t[6]=K("div",{class:"text-center"},[K("div",{class:"text-body-2 font-weight-light mb-n1"},"Welcome to"),K("h1",{class:"text-h2 font-weight-bold"},"Vuetify")],-1)),t[7]||(t[7]=K("div",{class:"py-4"},null,-1)),v(gn,null,{default:p(()=>[v(ee,{cols:"12"},{default:p(()=>[v(Z,{class:"py-4",color:"surface-variant",image:"https://cdn.vuetifyjs.com/docs/images/one/create/feature.png","prepend-icon":"mdi-rocket-launch-outline",rounded:"lg",variant:"outlined"},{image:p(()=>[v(Me,{position:"top right"})]),title:p(()=>t[0]||(t[0]=[K("h2",{class:"text-h5 font-weight-bold"},"Get started",-1)])),subtitle:p(()=>[K("div",Un,[t[3]||(t[3]=J(" Replace this page by removing ")),v(Ie,null,{default:p(()=>t[1]||(t[1]=[J(Jt("<HelloWorld />"))])),_:1}),t[4]||(t[4]=J(" in ")),v(Ie,null,{default:p(()=>t[2]||(t[2]=[J("pages/index.vue")])),_:1}),t[5]||(t[5]=J(". "))])]),default:p(()=>[v(ne,{opacity:".12",scrim:"primary",contained:"","model-value":"",persistent:""})]),_:1})]),_:1}),v(ee,{cols:"6"},{default:p(()=>[v(Z,{"append-icon":"mdi-open-in-new",class:"py-4",color:"surface-variant",href:"https://vuetifyjs.com/","prepend-icon":"mdi-text-box-outline",rel:"noopener noreferrer",rounded:"lg",subtitle:"Learn about all things Vuetify in our documentation.",target:"_blank",title:"Documentation",variant:"text"},{default:p(()=>[v(ne,{opacity:".06",scrim:"primary",contained:"","model-value":"",persistent:""})]),_:1})]),_:1}),v(ee,{cols:"6"},{default:p(()=>[v(Z,{"append-icon":"mdi-open-in-new",class:"py-4",color:"surface-variant",href:"https://vuetifyjs.com/introduction/why-vuetify/#feature-guides","prepend-icon":"mdi-star-circle-outline",rel:"noopener noreferrer",rounded:"lg",subtitle:"Explore available framework Features.",target:"_blank",title:"Features",variant:"text"},{default:p(()=>[v(ne,{opacity:".06",scrim:"primary",contained:"","model-value":"",persistent:""})]),_:1})]),_:1}),v(ee,{cols:"6"},{default:p(()=>[v(Z,{"append-icon":"mdi-open-in-new",class:"py-4",color:"surface-variant",href:"https://vuetifyjs.com/components/all","prepend-icon":"mdi-widgets-outline",rel:"noopener noreferrer",rounded:"lg",subtitle:"Discover components in the API Explorer.",target:"_blank",title:"Components",variant:"text"},{default:p(()=>[v(ne,{opacity:".06",scrim:"primary",contained:"","model-value":"",persistent:""})]),_:1})]),_:1}),v(ee,{cols:"6"},{default:p(()=>[v(Z,{"append-icon":"mdi-open-in-new",class:"py-4",color:"surface-variant",href:"https://discord.vuetifyjs.com","prepend-icon":"mdi-account-group-outline",rel:"noopener noreferrer",rounded:"lg",subtitle:"Connect with Vuetify developers.",target:"_blank",title:"Community",variant:"text"},{default:p(()=>[v(ne,{opacity:".06",scrim:"primary",contained:"","model-value":"",persistent:""})]),_:1})]),_:1})]),_:1})]),_:1})]),_:1}))}}),Xn=Ze({__name:"index",setup(e){return(n,t)=>{const o=Yn;return Je(),et(o)}}});export{Xn as default};
