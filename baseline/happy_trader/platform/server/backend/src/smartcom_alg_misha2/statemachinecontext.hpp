#ifndef _SMARTCOM_ALG_MISHA2_EVENTCONTEXT_INCLUDED
#define _SMARTCOM_ALG_MISHA2_EVENTCONTEXT_INCLUDED


#include "smartcom_alg_misha2defs.hpp"


namespace AlgLib {

	 
	class CAlgMisha2;
	class TradingSequenceRobot;

	// this helper class 
	class MachineContext: public CppUtils::BaseContext<MachineContext>
	{
		public:

			struct MachineContextStructure
			{
				MachineContextStructure(
					CppUtils::String const& symbol, 
					CAlgMisha2 &base, 
					TradingSequenceRobot& robot,
					CppUtils::String const& contextName
				):
					symbol_m(symbol),
					base_m(&base),
					name_m(contextName),
					robot_m(&robot)
				{
				}

				TradingSequenceRobot* robot_m;
				CAlgMisha2* base_m;
				CppUtils::String symbol_m;
				CppUtils::String name_m;
			
			};

			MachineContext(void* contextPtr);

			virtual ~MachineContext();

			bool isReverse() const;

			virtual void on_fatal_error(
				CppUtils::StateMachineException const& e
			);

			// set of logging functions
			virtual void on_state_changed(
				CppUtils::BaseState<MachineContext> const& exit_state, 
				CppUtils::BaseState<MachineContext> const& target_state,
				CppUtils::BaseState<MachineContext>::TransitionType const trans_type,
				CppUtils::BaseEvent const& event_i
			);

			virtual void on_entry_action(
				CppUtils::BaseState<MachineContext> const& target_state,
				CppUtils::BaseEvent const& event_i
			);
	
			virtual void on_exit_action(
				CppUtils::BaseState<MachineContext> const& exit_state,
				CppUtils::BaseEvent const& event_i
			);

			virtual void on_entry_action_execute(
				CppUtils::BaseState<MachineContext> const& target_state,
				CppUtils::BaseEvent const& event_i,
				CppUtils::BaseAction<MachineContext> const& action
			);
		

			virtual void on_exit_action_execute(
				CppUtils::BaseState<MachineContext> const& exit_state,
				CppUtils::BaseEvent const& event_i,
				CppUtils::BaseAction<MachineContext> const& action
			);
	

			virtual void on_delayed_event_register(CppUtils::BaseEvent const& event_i);

			virtual void on_direct_event_process(CppUtils::BaseEvent const& event_i);
		
			virtual void on_delayed_event_process(CppUtils::BaseEvent const& event_i);
		
			
			
			inline CAlgMisha2& getBase()
			{
				return *base_m;
			};

			inline TradingSequenceRobot& getRobot()
			{
				return *robot_m;
			};

			inline CppUtils::String const& getMachineSymbol()	const
			{
				return machineSymbol_m;
			}

			CppUtils::String getContext() const
			{
				return machineSymbol_m + " - " + "[ " + name_m + " ] ";
			};

			inline CppUtils::String const& getName() const
			{
				return name_m;
			};

			
			
		private:

			TradingSequenceRobot* robot_m;

			CAlgMisha2* base_m;
			

			CppUtils::String machineSymbol_m;

			CppUtils::String name_m;

			


	};

	

};

#endif