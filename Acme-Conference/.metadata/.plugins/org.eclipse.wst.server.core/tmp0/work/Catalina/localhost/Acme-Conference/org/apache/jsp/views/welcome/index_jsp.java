package org.apache.jsp.views.welcome;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.List<java.lang.String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjstl_005fchoose;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjstl_005fwhen_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjstl_005fout_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjstl_005fotherwise;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.List<java.lang.String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fjstl_005fchoose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fjstl_005fwhen_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fjstl_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fjstl_005fotherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fjstl_005fchoose.release();
    _005fjspx_005ftagPool_005fjstl_005fwhen_0026_005ftest.release();
    _005fjspx_005ftagPool_005fjstl_005fout_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fjstl_005fotherwise.release();
    _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_jstl_005fchoose_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("<p>\r\n");
      out.write("\t");
      if (_jspx_meth_spring_005fmessage_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write('	');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${moment}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("</p>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_jstl_005fchoose_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  jstl:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_jstl_005fchoose_005f0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _005fjspx_005ftagPool_005fjstl_005fchoose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_jstl_005fchoose_005f0.setPageContext(_jspx_page_context);
    _jspx_th_jstl_005fchoose_005f0.setParent(null);
    int _jspx_eval_jstl_005fchoose_005f0 = _jspx_th_jstl_005fchoose_005f0.doStartTag();
    if (_jspx_eval_jstl_005fchoose_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_jstl_005fwhen_005f0(_jspx_th_jstl_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_jstl_005fotherwise_005f0(_jspx_th_jstl_005fchoose_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_jstl_005fchoose_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_jstl_005fchoose_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjstl_005fchoose.reuse(_jspx_th_jstl_005fchoose_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fjstl_005fchoose.reuse(_jspx_th_jstl_005fchoose_005f0);
    return false;
  }

  private boolean _jspx_meth_jstl_005fwhen_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_jstl_005fchoose_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  jstl:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_jstl_005fwhen_005f0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _005fjspx_005ftagPool_005fjstl_005fwhen_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_jstl_005fwhen_005f0.setPageContext(_jspx_page_context);
    _jspx_th_jstl_005fwhen_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_jstl_005fchoose_005f0);
    // /views/welcome/index.jsp(22,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_jstl_005fwhen_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.response.locale.language == 'es'}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_jstl_005fwhen_005f0 = _jspx_th_jstl_005fwhen_005f0.doStartTag();
    if (_jspx_eval_jstl_005fwhen_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t<p>\r\n");
        out.write("\t\t\t");
        if (_jspx_meth_jstl_005fout_005f0(_jspx_th_jstl_005fwhen_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t</p>\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_jstl_005fwhen_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_jstl_005fwhen_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjstl_005fwhen_0026_005ftest.reuse(_jspx_th_jstl_005fwhen_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fjstl_005fwhen_0026_005ftest.reuse(_jspx_th_jstl_005fwhen_005f0);
    return false;
  }

  private boolean _jspx_meth_jstl_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_jstl_005fwhen_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  jstl:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_jstl_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fjstl_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_jstl_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_jstl_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_jstl_005fwhen_005f0);
    // /views/welcome/index.jsp(24,3) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_jstl_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${welcomeMessage.get('Español')}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    int _jspx_eval_jstl_005fout_005f0 = _jspx_th_jstl_005fout_005f0.doStartTag();
    if (_jspx_th_jstl_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjstl_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_jstl_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fjstl_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_jstl_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_jstl_005fotherwise_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_jstl_005fchoose_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  jstl:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_jstl_005fotherwise_005f0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _005fjspx_005ftagPool_005fjstl_005fotherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_jstl_005fotherwise_005f0.setPageContext(_jspx_page_context);
    _jspx_th_jstl_005fotherwise_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_jstl_005fchoose_005f0);
    int _jspx_eval_jstl_005fotherwise_005f0 = _jspx_th_jstl_005fotherwise_005f0.doStartTag();
    if (_jspx_eval_jstl_005fotherwise_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t<p>\r\n");
        out.write("\t\t\t");
        if (_jspx_meth_jstl_005fout_005f1(_jspx_th_jstl_005fotherwise_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t</p>\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_jstl_005fotherwise_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_jstl_005fotherwise_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjstl_005fotherwise.reuse(_jspx_th_jstl_005fotherwise_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fjstl_005fotherwise.reuse(_jspx_th_jstl_005fotherwise_005f0);
    return false;
  }

  private boolean _jspx_meth_jstl_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_jstl_005fotherwise_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  jstl:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_jstl_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fjstl_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_jstl_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_jstl_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_jstl_005fotherwise_005f0);
    // /views/welcome/index.jsp(29,3) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_jstl_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${welcomeMessage.get('English')}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    int _jspx_eval_jstl_005fout_005f1 = _jspx_th_jstl_005fout_005f1.doStartTag();
    if (_jspx_th_jstl_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjstl_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_jstl_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fjstl_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_jstl_005fout_005f1);
    return false;
  }

  private boolean _jspx_meth_spring_005fmessage_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_005fmessage_005f0 = (org.springframework.web.servlet.tags.MessageTag) _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_spring_005fmessage_005f0.setParent(null);
    // /views/welcome/index.jsp(35,1) name = code type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f0.setCode("welcome.greeting.current.time");
    int[] _jspx_push_body_count_spring_005fmessage_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_spring_005fmessage_005f0 = _jspx_th_spring_005fmessage_005f0.doStartTag();
      if (_jspx_th_spring_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_005fmessage_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_005fmessage_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_005fmessage_005f0.doFinally();
      _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.reuse(_jspx_th_spring_005fmessage_005f0);
    }
    return false;
  }
}
