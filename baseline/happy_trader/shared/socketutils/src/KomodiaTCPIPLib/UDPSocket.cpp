/*
 *  Copyright (c) 2000-2003 Barak Weichselbaum <barak@komodia.com>
 *  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * Contact info:
 * -------------
 *
 * Site:					http://www.komodia.com
 * Main contact:			barak@komodia.com
 * For custom projects, 
 * consulting, or other
 * paid services:			sales@komodia.com
 */

#include "stdafx.h"
#include "UDPSocket.h"

#include "../KomodiaInfraLib/ErrorHandlerMacros.h"
#include "Array_ptr.h"

#include <memory>

#ifdef _MEMORY_DEBUG 
	#define new	   DEBUG_NEW  
	#define malloc DEBUG_MALLOC  
    static char THIS_FILE[] = __FILE__;  
#endif

KOMODIA_NAMESPACE_START

#define CUDPSocket_Class "CUDPSocket"

CUDPSocket::CUDPSocket(BOOL bRawSocket) : CSocket(bRawSocket)
{	
	try
	{
		//Set our name
		SetName(CUDPSocket_Class);
	}
	ERROR_HANDLER("CUDPSocket")
}

CUDPSocket::~CUDPSocket()
{
}

BOOL CUDPSocket::Create()
{
	try
	{
		//Set to UDP
		SetProtocol(IPPROTO_UDP);

		//Try to create
		return CSocket::Create(IPPROTO_UDP);
	}
	ERROR_HANDLER_RETURN("Create",FALSE)
}

BOOL CUDPSocket::Send(IP aDestinationAddress, 
					  unsigned short usDestinationPort,
					  const char* pBuffer,
					  unsigned short usBufferLength)
{
	try
	{
		//Delegate the call
		return CSocket::Send(aDestinationAddress,
							 pBuffer,
							 usBufferLength,
							 usDestinationPort);
	}
	ERROR_HANDLER_RETURN("Send",FALSE)
}

BOOL CUDPSocket::Send(const std::string& rDestinationAddress, 
					  unsigned short usDestinationPort,
					  const char* pBuffer,
					  unsigned short usBufferLength)
{
	try
	{
		//Delegate the call
		return Send(rDestinationAddress,
					usDestinationPort,
					pBuffer,
					usBufferLength);
	}
	ERROR_HANDLER_RETURN("Send",FALSE)
}

int CUDPSocket::Receive(char* pBuffer,
						unsigned long ulBufferLength)
{
	try
	{
		//Delegate call
		return CSocket::Receive(pBuffer,
								ulBufferLength);
	}
	ERROR_HANDLER_RETURN("Receive",GetErrorCode())
}

int CUDPSocket::Receive(char* pBuffer,
						unsigned long ulBufferLength,
						IP& rIP,
						unsigned short& rSourcePort)
{
	try
	{
		//Delegate call
		return ReceiveFrom(pBuffer,
						   ulBufferLength,
						   rIP,
						   rSourcePort);
	}
	ERROR_HANDLER_RETURN("Receive",GetErrorCode())
}

KOMODIA_NAMESPACE_END