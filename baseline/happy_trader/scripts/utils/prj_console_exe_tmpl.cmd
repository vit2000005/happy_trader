@echo off
echo ^<?xml version="1.0" encoding="windows-1251"?^>
echo ^<VisualStudioProject
echo	ProjectType="Visual C++"
echo	Version="7.10"
echo	Name="%BASE_NAME%"
echo	ProjectGUID="{%UID_GEN%}"
echo	SccProjectName=""
echo	SccLocalPath=""^>
echo	^<Platforms^>
echo		^<Platform
echo			Name="Win32"/^>
echo	^</Platforms^>
echo	^<Configurations^>
echo		^<Configuration
echo			Name="Debug|Win32"
echo			OutputDirectory="../../obj/Debug/%SUB_DIR%"
echo			IntermediateDirectory="../../obj/Debug/%SUB_DIR%"
echo			ConfigurationType="1"
echo			UseOfMFC="0"
echo			ATLMinimizesCRunTimeLibraryUsage="FALSE"
echo			CharacterSet="2"^>
echo			^<Tool
echo				Name="VCCLCompilerTool"
echo				AdditionalOptions="
echo "
echo				Optimization="0"
echo				AdditionalIncludeDirectories="&quot;$(HT_DEVEL_ROOT)/extern/include/ooc/ob/410&quot;;&quot;$(HT_DEVEL_ROOT)/extern/include/ooc/jtc/200&quot;;&quot;$(HT_DEVEL_ROOT)/extern/include&quot;;&quot;$(HT_DEVEL_ROOT)&quot;"
echo				PreprocessorDefinitions="_DEBUG;_CONSOLE;CPPUTILS_DLL;SERVICE_BACKEND_DLLS;WIN32;HANDLE_NATIVE_EXCEPTIONS"
echo				BasicRuntimeChecks="3"
echo				RuntimeLibrary="3"
echo				RuntimeTypeInfo="TRUE"
echo				UsePrecompiledHeader="2"
echo				PrecompiledHeaderFile=".\../../obj/Debug/%SUB_DIR%/%PCH_NAME%"
echo				AssemblerListingLocation=".\../../obj/Debug/%SUB_DIR%/"
echo				ObjectFile=".\../../obj/Debug/%SUB_DIR%/"
echo				ProgramDataBaseFileName=".\../../obj/Debug/%SUB_DIR%/"
echo				BrowseInformation="1"
echo				BrowseInformationFile=".\../../obj/Debug/%SUB_DIR%/"
echo				WarningLevel="3"
echo				SuppressStartupBanner="TRUE"
echo				DebugInformationFormat="3"
echo				CompileAs="2"/^>
echo			^<Tool
echo				Name="VCCustomBuildTool"/^>
echo			^<Tool
echo				Name="VCLinkerTool"
echo				AdditionalOptions="/FIXED:NO"
echo				AdditionalDependencies="comctl32.lib netapi32.lib odbc32.lib odbccp32.lib"
echo				OutputFile="../../../../bin/win32_i86/Debug/%EXE_NAME_DEBUG%"
echo				Version="2.1"
echo				LinkIncremental="1"
echo				SuppressStartupBanner="TRUE"
echo				AdditionalLibraryDirectories="$(HT_DEVEL_ROOT)/extern/lib/win32_i86/sxl"
echo				IgnoreDefaultLibraryNames=""
echo				GenerateDebugInformation="TRUE"
echo				ProgramDatabaseFile=".\../../obj/Debug/htmain_srv/%PDB_NAME_DEBUG%"
echo				SubSystem="1"
echo				TargetMachine="1"/^>
echo			^<Tool
echo				Name="VCMIDLTool"
echo				TypeLibraryName=".\../../../../bin/win32_i86/Debug/%TLB_NAME%"
echo				HeaderFileName=""/^>
echo			^<Tool
echo				Name="VCPostBuildEventTool"
echo				Description="Copying EXE"
echo				CommandLine="copy                               ..\..\..\..\bin\win32_i86\Debug\%EXE_NAME_DEBUG%                               ..\..\..\..\..\..\bin\win32_i86\Debug\"/^>
echo			^<Tool
echo				Name="VCPreBuildEventTool"/^>
echo			^<Tool
echo				Name="VCPreLinkEventTool"/^>
echo			^<Tool
echo				Name="VCResourceCompilerTool"
echo				PreprocessorDefinitions="_DEBUG"
echo				Culture="1031"/^>
echo			^<Tool
echo				Name="VCWebServiceProxyGeneratorTool"/^>
echo			^<Tool
echo				Name="VCXMLDataGeneratorTool"/^>
echo			^<Tool
echo				Name="VCWebDeploymentTool"/^>
echo			^<Tool
echo				Name="VCManagedWrapperGeneratorTool"/^>
echo			^<Tool
echo				Name="VCAuxiliaryManagedWrapperGeneratorTool"/^>
echo		^</Configuration^>
echo		^<Configuration
echo			Name="Release|Win32"
echo			OutputDirectory="../../obj/Release/%SUB_DIR%"
echo			IntermediateDirectory="../../obj/Release/%SUB_DIR%"
echo			ConfigurationType="1"
echo			UseOfMFC="0"
echo			ATLMinimizesCRunTimeLibraryUsage="FALSE"
echo			CharacterSet="2"^>
echo			^<Tool
echo				Name="VCCLCompilerTool"
echo				Optimization="4"
echo				InlineFunctionExpansion="1"
echo				EnableIntrinsicFunctions="TRUE"
echo				FavorSizeOrSpeed="1"
echo				OmitFramePointers="TRUE"
echo				AdditionalIncludeDirectories="&quot;$(HT_DEVEL_ROOT)/extern/include/ooc/ob/410&quot;;&quot;$(HT_DEVEL_ROOT)/extern/include/ooc/jtc/200&quot;;&quot;$(HT_DEVEL_ROOT)/extern/include&quot;;&quot;$(HT_DEVEL_ROOT)&quot;;&quot;$(HT_DEVEL_ROOT)/extern/include/sxl&quot;"
echo				PreprocessorDefinitions="NDEBUG;_CONSOLE;CPPUTILS_DLL;SERVICE_BACKEND_DLLS;WIN32;ORBACUS4;OB_CLEAR_MEM;HANDLE_NATIVE_EXCEPTIONS"
echo				RuntimeLibrary="2"
echo				RuntimeTypeInfo="TRUE"
echo				UsePrecompiledHeader="2"
echo				PrecompiledHeaderFile=".\../../obj/Release/%SUB_DIR%/%PCH_NAME%"
echo				AssemblerListingLocation=".\../../obj/Release/%SUB_DIR%/"
echo				ObjectFile=".\../../obj/Release/%SUB_DIR%/"
echo				ProgramDataBaseFileName=".\../../obj/Release/%SUB_DIR%/"
echo				WarningLevel="3"
echo				SuppressStartupBanner="TRUE"
echo				CompileAs="0"/^>
echo			^<Tool
echo				Name="VCCustomBuildTool"/^>
echo			^<Tool
echo				Name="VCLinkerTool"
echo				AdditionalDependencies="comctl32.lib netapi32.lib odbc32.lib odbccp32.lib"
echo				OutputFile=".\../../../../bin/win32_i86/Release/%EXE_NAME_RELEASE%"
echo				Version="2.1"
echo				LinkIncremental="1"
echo				SuppressStartupBanner="TRUE"
echo				AdditionalLibraryDirectories="$(HT_DEVEL_ROOT)/extern/lib/win32_i86/sxl"
echo				IgnoreDefaultLibraryNames=""
echo				ProgramDatabaseFile=".\../../obj/Release/htmain_srv/%PDB_NAME_RELEASE%"
echo				SubSystem="1"
echo				TargetMachine="1"/^>
echo			^<Tool
echo				Name="VCMIDLTool"
echo				TypeLibraryName=".\../../../../bin/win32_i86/Release/%TLB_NAME%"
echo				HeaderFileName=""/^>
echo			^<Tool
echo				Name="VCPostBuildEventTool"
echo				Description="Copying EXE"
echo				CommandLine="copy                               ..\..\..\..\bin\win32_i86\Release\%EXE_NAME_RELEASE%                               ..\..\..\..\..\..\bin\win32_i86\Release\"/^>
echo			^<Tool
echo				Name="VCPreBuildEventTool"/^>
echo			^<Tool
echo				Name="VCPreLinkEventTool"/^>
echo			^<Tool
echo				Name="VCResourceCompilerTool"
echo				PreprocessorDefinitions="NDEBUG"
echo				Culture="1031"/^>
echo			^<Tool
echo				Name="VCWebServiceProxyGeneratorTool"/^>
echo			^<Tool
echo				Name="VCXMLDataGeneratorTool"/^>
echo			^<Tool
echo				Name="VCWebDeploymentTool"/^>
echo			^<Tool
echo				Name="VCManagedWrapperGeneratorTool"/^>
echo			^<Tool
echo				Name="VCAuxiliaryManagedWrapperGeneratorTool"/^>
echo		^</Configuration^>
echo	^</Configurations^>
echo	^<References^>
echo	^</References^>
echo	^<Files^>
echo		^<Filter
echo			Name="Source Files"
echo			Filter="cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"^>
echo			^<File
echo				RelativePath="..\..\src\%SUB_DIR%\%MAIN_FILE_NAME%"^>
echo			^</File^>
echo		^</Filter^>
echo		^<Filter
echo			Name="Header Files"
echo			Filter=""^>
echo		^</Filter^>
echo	^</Files^>
echo	^<Globals^>
echo	^</Globals^>
echo ^</VisualStudioProject^>